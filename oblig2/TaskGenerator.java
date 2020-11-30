import java.util.HashMap;
import java.util.LinkedList;

class TaskGenerator{
    private HashMap<Integer, Task> tasks;

    TaskGenerator(){
        tasks = new HashMap<>();
    }

    void addTask(int id, String name, int time, int staff, int[] dependencies){
        Task newTasks = new Task(id, name, time, staff, dependencies);
        tasks.put(id, newTasks);
    }

   void update(){
        for (Task t: tasks.values()) {
            for (int i = 0; i <t.dependencies.length ; i++) {
                Task getUpdated = tasks.get(t.dependencies[i]);

                if (getUpdated.outEdges == null){
                    getUpdated.outEdges = new int[1];
                    getUpdated.outEdges[0] = t.id;
                }else{
                    int[] arr = new int[getUpdated.outEdges.length + 1];

                    System.arraycopy(getUpdated.outEdges, 0, arr, 0, getUpdated.outEdges.length);
                    arr[getUpdated.outEdges.length] = t.id;
                    getUpdated.outEdges = arr;
                }
            }
        }
    }

    LinkedList<Task> loopTasks(){
        for (Task t: tasks.values()){
             System.out.println("Call loop from loopTasks");
            LinkedList<Task> taskCount = loop(t, new LinkedList<>());
            if (taskCount != null){
                return taskCount;
            }
        }
        return null;
    }

    LinkedList<Task> loop(Task t, LinkedList<Task> list){//DFS  som hjelpe metode for loopTasks
          System.out.println("loop: List: "+ list);
        if (list.contains(t)){
            while (list.peek() != t) list.poll();
            list.add(t);
            return list;
        }else{
            list.add(t);
            if (t.outEdges != null){
                for (int i = 0; i<t.outEdges.length; i++){
                    assert list != null;
                    System.out.println("Call loop(" + tasks.get(t.outEdges[i]) + "," + list + ")");
                    LinkedList<Task> tempList = loop(tasks.get(t.outEdges[i]), list);
                    System.out.println("list after return:" + list);
                    if (tempList != null){
                        return tempList;
                    }
                }
            }
            //else {
              //  return null;
            //}
        }
        list.remove(t);
        return null;
    }

    LinkedList<Task> sort(){
        LinkedList<Task> list = new LinkedList<>();
        boolean loops;
        while (list.size() < tasks.size()){
            loops = true;
            for (Task t: tasks.values()) {
                if (t.predecessorsCount - t.visitCount == 0 && !t.visited){
                    t.visited = true;
                    loops = false;
                    list.add(t);
                    visit(t);
                }
            }
            if (loops == true){//vi har en loop hvis vi ikke finnes noen task
                System.out.println("Running in loop");
                return null;
            }
        }

        for (Task t : tasks.values()) {
            t.visitCount = 0;
            t.visited = false;
        }
        return list;
    }

    void printSort(){//her skrives ut alle taskene
        int startTime = -1;
        int runingTasks = 0;
        int currentStaff = 0;

        LinkedList<Task> queue = new LinkedList<>();

        while (queue.size() < tasks.size() || runingTasks > 0){
            startTime++;
            StringBuilder printout = new StringBuilder("Time: " + startTime + "\n");
            boolean print = false;

            for (Task t: tasks.values()) {
                if (queue.contains(t) && t.predecessorsCount - t.visitCount== 0 && !t.visited && (startTime == t.earliestStart + t.time)){
                    printout.append("\tFinished: ").append(t.id).append("\n");
                    print = true;
                    t.visited = true;
                    runingTasks--;
                    currentStaff -= t.staff;
                    visit(t);
                }
            }
            //sjekker om vi kan starte en ny task
            for (Task t: tasks.values()) {
                if (t.predecessorsCount - t.visitCount == 0 && !t.visited && !queue.contains(t)){
                    queue.add(t);
                    printout.append("\tStarting: ").append(t.id).append("\n");
                    print = true;
                    t.earliestStart = startTime;
                    runingTasks++;
                    currentStaff += t.staff;
                }
            }
            for (Task t: tasks.values()) {
                if (t.visited && t.outEdges != null) {
                    boolean started = false;
                    for (int i = 0; i < t.outEdges.length; i++) {
                        if (queue.contains(tasks.get(t.outEdges[i]))) {
                            started = true;
                        }
                    }
                    if (!started) {
                        t.slackCount++;
                    }
                } else if (t.visited) {
                        if (runingTasks > 0) {
                            t.slackCount++;
                        }
                }
            }
            if (currentStaff > 0){
                printout.append("\tCurrent Staff: ").append(currentStaff).append("\n");
            }
            if (print){
                System.out.println(printout);
            }
        }
        System.out.println("**** Shortest possible project execution is " + startTime + " ****");

        System.out.println(" ID  \tName   \t\t\t\t  Time Needed\t\tManPower  \tEarliest Start Latest Start  \tSlack\tEdges");
         for (Task t: tasks.values()){
             t.latestStart = t.earliestStart + t.slackCount;
             System.out.format("%3d\t%-40s%3d%15d%17d%16d %15d\t", t.getId(), t.getName(), t.getTime(), t.getStaff(), t.getEarliestStart(), t.getLatestStart(), t.slackCount);
             t.printEdges();
         }
    }

    void visit(Task t) {
        for (int i = 0;t.outEdges != null && i < t.outEdges.length; i++) {
            Task edge = tasks.get(t.outEdges[i]);
            edge.visitCount++;
        }
    }
}
