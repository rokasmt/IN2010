import java.util.Scanner;
import java.io.File;
import java.util.LinkedList;
import java.io.IOException;
class LesFraFil{

	private TaskGenerator tasks;

	void LesFraFil(String file, TaskGenerator tasks){
	    this.tasks = tasks;

	    try{
	        Scanner sc = new Scanner(new File(file));
		  Integer.parseInt(sc.nextLine());
            while(sc.hasNext()){
                String line = sc.nextLine().trim();
                if (line.isEmpty()){
                    continue;
                }
                String[] lines = line.split("\\s+");
                int id = Integer.parseInt(lines[0].trim());

                String name = lines[1].trim();
                int time = Integer.parseInt(lines[2].trim());
                int staff = Integer.parseInt(lines[3].trim());

                int[] edges = new int[lines.length-5];
                int x = 4;
                while (x < lines.length-1){
                    edges[x-4] = Integer.parseInt(lines[x].trim());
                    x++;
                }
                tasks.addTask(id, name, time, staff, edges);//add to task
            }
            sc.close();
        }catch (IOException e){
	        e.printStackTrace();
        }
    }

    void update(){
	    if (tasks == null){
	        System.out.print("Graph is empty");
        }
        tasks.update();
    }

    void sort(){
        LinkedList<Task> t = tasks.sort();
        if (t != null){//Hvis t=null da får vi en loop
            tasks.printSort();
        }else{
           loopTasks();
        }
    }

	void loopTasks(){
        LinkedList<Task> t = tasks.loopTasks();
        if (t != null){
            while (t.size() > 0)System.out.print(t.poll().id+"-->");
             System.out.println();
        }
    }
}
