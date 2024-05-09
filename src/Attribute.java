import java.util.*;

public class Attribute {

    float[] attributes;
    String decisionAttribute;

    public Attribute(String decisionAttribute, float[] attributes) {
        this.attributes = attributes;
        this.decisionAttribute = decisionAttribute;
    }

    public static void train(List<Attribute> allAttributes, float[] weightVector, float learningConst) {
        float t = weightVector[weightVector.length - 1] * (-1);
        System.out.println(t);

        String y1 = "";
        String y0 = "";
        y1 = allAttributes.get(0).decisionAttribute;
        for (int i = 0; i < allAttributes.size(); i++) {
            if (!y1.equals(allAttributes.get(i).decisionAttribute)){
                y0 = allAttributes.get(i).decisionAttribute;
                break;
            }
        }

        System.out.println(y0);
        System.out.println(y1 + "\n");


        int accuracy = 0;
        for (Attribute att : allAttributes) {
            int y = countY(att, weightVector);

            accuracy += y;
            int d = 0;
            if (att.decisionAttribute.equals(y0))
                d = 0;
            else {
                d=1;
            }

            System.out.println(att.decisionAttribute + " " + y);

            weightVector = wPrim(att.attributes, weightVector, y, d, learningConst);

            t = tPrim(t, y, d, learningConst);


            y = countY(att, weightVector);

            System.out.println(att.decisionAttribute + " " + y);


        }

        System.out.println("\n\nAfter delta");
        for (Attribute att : allAttributes) {
            int y = countY(att, weightVector);
            System.out.println(att.decisionAttribute + " " + y);
        }

    }

    public static int countY(Attribute att, float[] weightVector) {
        float net = 0;
        for (int i = 0; i < att.attributes.length; i++) {

            net += att.attributes[i] * weightVector[i];
//            System.out.println(net + " = " + att.attributes[i] +" * " + weightVector[i]);

        }
        net -= weightVector[weightVector.length - 1];

        int y;

        if (net >= 0)
            y = 1;
        else
            y = 0;

        return y;
    }

    public static float[] wPrim(float[] attributes, float[] weightVector, int y, int d, float learningConst) {
//        float[] wPrim = new float[weightVector.length];

        if (y == 0)
            d = 1;
        else
            d = 1;

        for (int i = 0; i < weightVector.length; i++) {
            weightVector[i] = weightVector[i] + (d - y) * attributes[i] * learningConst;
        }
        return weightVector;
    }

    public static float tPrim(float t, int y, int d, float learningConst) {
        return t - (d - y) * learningConst;
    }

    public static void perceptron() {

    }

    public static String countMajority(List<String> closest) {
        String majority = "";
        Map<String, Integer> countDecisionAttributes = new HashMap<>();
        for (String close : closest) {
            int count = countDecisionAttributes.getOrDefault(close, 0);
            countDecisionAttributes.put(close, count + 1);
        }
        int maximum = -1;
        for (Map.Entry<String, Integer> entry : countDecisionAttributes.entrySet()) {
            if (entry.getValue() > maximum) {
                majority = entry.getKey();
                maximum = entry.getValue();
            }
        }
        return majority;
    }


    public static void print(List<Attribute> list) {
        for (Attribute att : list) {
            for (int i = 0; i < att.attributes.length; i++) {
                System.out.print(att.attributes[i] + " ");
            }
            System.out.println(att.decisionAttribute);

        }
    }
}
