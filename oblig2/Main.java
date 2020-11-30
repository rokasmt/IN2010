public class Main {

    public static void main(String[] args) {
        String filename = args[0].trim();

        LesFraFil reader = new LesFraFil();
        TaskGenerator generator = new TaskGenerator();
        reader.LesFraFil(filename, generator);
        reader.update();
        reader.sort();
    }
}
