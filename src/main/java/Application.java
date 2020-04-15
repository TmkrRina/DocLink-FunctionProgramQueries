import data.DataBuilder;
<<<<<<< HEAD
import functions.FunctionForQ;
import models.*;
=======
import functions.DocLinkFunctions;
import models.Post;
import models.PostType;
import models.User;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
>>>>>>> d987a1264e6ba0938c298e100e4089f82a03417b

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

