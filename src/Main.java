import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        float learningConst = Float.parseFloat(args[0]);

        Random random = new Random();

        List<Attribute> allAttributes = new ArrayList<>();
        List<String[]> trainSet = readFile(args[1]);

        for (String[] arr : trainSet) {
            float[] att = new float[arr.length - 1];
            for (int i = 0; i < arr.length - 1; i++) {
                att[i] = Float.parseFloat(arr[i]);
            }
            String decAtt = arr[arr.length - 1];

            Attribute attribute = new Attribute(decAtt, att);
            allAttributes.add(attribute);

        }
        Collections.shuffle(allAttributes);

        float[] weightVector = new float[trainSet.get(0).length - 1];
        for (int i = 0; i < weightVector.length; i++) {
            float val = random.nextFloat(11) - 5;
            weightVector[i] = val;
            System.out.print(weightVector[i] + " ");
        }
        System.out.println();

        Attribute.train(allAttributes, weightVector, learningConst);

//        for (float w : weightVector) {
//            System.out.print(w + " ");
//        }
    }


    public static List<String[]> readFile(String path) {
        List<String[]> data = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] line = reader.nextLine().split(";");
                data.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}