import data.DataBuilder;
import functions.FunctionForQ;

import java.util.Date;

public class Application  {
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.run();

        try {
            DataBuilder.setUp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



    }

