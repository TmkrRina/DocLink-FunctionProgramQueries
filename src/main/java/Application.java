import data.DataBuilder;
import functions.FunctionForQ;
import models.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

