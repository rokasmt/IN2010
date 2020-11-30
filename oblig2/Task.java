class Task{
    int id, time, staff;
    private String name;
    int[] outEdges;
    int earliestStart, latestStart;
    int predecessorsCount;
    int slackCount;
    int visitCount;
    boolean visited;
    int[] dependencies;

    Task(int id, String name, int time, int staff, int[] dependencies){
        this.id = id;
        this.name = name;
        this.time = time;
        this.staff = staff;
        predecessorsCount = dependencies.length;
        slackCount = 0;
        this.visitCount = 0;
        visited = false;
        this.dependencies = dependencies;
 }

    int getId() {
        return id;
    }

    int getTime() {
        return time;
    }

    int getStaff() {
        return staff;
    }

    String getName() {
        return name;
    }

    int getEarliestStart() {
        return earliestStart;
    }

    int getLatestStart() {
        return latestStart;
    }

    void printEdges(){
        if (outEdges == null){
            System.out.println();
            return;
        }
        for (int outEdge : outEdges) {
            System.out.print(outEdge + " ");
        }
        System.out.println();
    }
}
