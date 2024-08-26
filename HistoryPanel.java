import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.BorderLayout; 
import java.nio.file.*;
import java.awt.*;
import java.net.*;
import java.nio.file.*;

public class HistoryPanel extends JPanel {

    public HistoryPanel() {
        try {
            // Windows Look and Feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());

        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");

        int nc = 0;
        try {

            String programDirectoryName = ".DASHAPP";
            String fileName = "tasksave.txt";

            // Get the user's home directory
            String userHome = System.getProperty("user.home");

            // Construct the full path to the .myProgram directory
            File programDirectory = new File(userHome, programDirectoryName);

            // Create the .myProgram directory if it doesn't exist
            if (!programDirectory.exists()) {
                boolean created = programDirectory.mkdirs(); // Creates the directory and any necessary parent directories
                if (created) {
                    System.out.println("Directory created: " + programDirectory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + programDirectory.getAbsolutePath());
                }
            }

            // Construct the full path to the file inside the .myProgram directory
            File file = new File(programDirectory, fileName);

            // Create the file if it doesn't exist
            if (!file.exists()) {
                try {
                    boolean created = file.createNewFile();
                    if (created) {
                        System.out.println("File created: " + file.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create file: " + file.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.err.println("Error creating file: " + e.getMessage());
                }
            }

          
            Scanner sc = new Scanner(file);

            //Scanner sc = new Scanner(new File("tasksave.txt"));
            while (sc.hasNext()) {
                String clr = sc.nextLine();
                nc++;
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        String tlist[] = new String[nc];
        int nr = 0;
        try {
            String programDirectoryName = ".DASHAPP";
            String fileName = "tasksave.txt";

            // Get the user's home directory
            String userHome = System.getProperty("user.home");

            // Construct the full path to the .myProgram directory
            File programDirectory = new File(userHome, programDirectoryName);

            // Create the .myProgram directory if it doesn't exist
            if (!programDirectory.exists()) {
                boolean created = programDirectory.mkdirs(); // Creates the directory and any necessary parent directories
                if (created) {
                    System.out.println("Directory created: " + programDirectory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + programDirectory.getAbsolutePath());
                }
            }

            // Construct the full path to the file inside the .myProgram directory
            File file = new File(programDirectory, fileName);

            // Create the file if it doesn't exist
            if (!file.exists()) {
                try {
                    boolean created = file.createNewFile();
                    if (created) {
                        System.out.println("File created: " + file.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create file: " + file.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.err.println("Error creating file: " + e.getMessage());
                }
            }

           
            Scanner sc = new Scanner(file);

            //Scanner sc = new Scanner(new File("tasksave.txt"));
            while (sc.hasNext()) {
                String str = sc.nextLine();
                tlist[nr] = str;
                nr++;
            }
            for (int i = 0; i < nc; i++)
                System.out.println(tlist[i]);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        StringBuilder htmlText = new StringBuilder("<html><body style='font-family: Georgia, sans-serif; background-color: #6ac4cb; color: #273c7d; text-align: center;'>" +
                "<h1 style='font-size: 20px;'>Your Task Archive</h1>" +
                "<div style='text-align: left;'>"); // Div to ensure text is left-aligned

        for (int i = 0; i < nc; i++) {
            if (tlist[i].contains("#")) {
                String s = tlist[i].substring(tlist[i].indexOf('#') + 1);
                htmlText.append("<p style='font-size: 12px;'><b>").append(s).append("</b>");
            } else {
                String s = tlist[i];
                htmlText.append("<p style='font-size: 12px; color: #FFFFFF; font-style: italic;'>").append(s);
            }
        }

        htmlText.append("</div></body></html>");

        editorPane.setText(htmlText.toString());

        editorPane.setEditable(false); // Make text area non-editable
        JScrollPane scrollPane = new JScrollPane(editorPane); // Add scrolling capability

        add(scrollPane, BorderLayout.CENTER);
    }
    static String history="";
    public static void add(String tas) throws IOException
    {
        try {
            // Windows Look and Feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String programDirectoryName = ".DASHAPP";
            String fileName = "tasksave.txt";

            // Get the user's home directory
            String userHome = System.getProperty("user.home");

            // Construct the full path to the .myProgram directory
            File programDirectory = new File(userHome, programDirectoryName);

            // Create the .myProgram directory if it doesn't exist
            if (!programDirectory.exists()) {
                boolean created = programDirectory.mkdirs(); // Creates the directory and any necessary parent directories
                if (created) {
                    System.out.println("Directory created: " + programDirectory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + programDirectory.getAbsolutePath());
                }
            }

            // Construct the full path to the file inside the .myProgram directory
            File file = new File(programDirectory, fileName);

            // Create the file if it doesn't exist
            if (!file.exists()) {
                try {
                    boolean created = file.createNewFile();
                    if (created) {
                        System.out.println("File created: " + file.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create file: " + file.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.err.println("Error creating file: " + e.getMessage());
                }
            }

           
            Scanner sc = new Scanner(file);

            //Scanner sc = new Scanner(new File("tasksave.txt"));

            String programDirectoryName1 = ".DASHAPP";
            String fileName1 = "temp.txt";

            // Get the user's home directory
            String userHome1 = System.getProperty("user.home");

            // Construct the full path to the .myProgram directory
            File programDirectory1 = new File(userHome1, programDirectoryName1);

            // Create the .myProgram directory if it doesn't exist
            if (!programDirectory1.exists()) {
                boolean created = programDirectory1.mkdirs(); // Creates the directory and any necessary parent directories
                if (created) {
                    System.out.println("Directory created: " + programDirectory1.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + programDirectory1.getAbsolutePath());
                }
            }

            // Construct the full path to the file inside the .myProgram directory
            File file1 = new File(programDirectory1, fileName1);

            // Create the FileWriter
            FileWriter fw = null;
            try {
                fw = new FileWriter(file1, false); // true for append mode
            } catch (IOException e) {
                System.err.println("Error creating FileWriter: " + e.getMessage());
            }

            // Create the BufferedWriter and PrintWriter
            BufferedWriter br = new BufferedWriter(fw);
            PrintWriter pr = new PrintWriter(br);

            //FileWriter fr=new FileWriter("temp.txt",true);
            //Path pathObject = Paths.get("temp.txt");
            //Files.newOutputStream(pathObject, StandardOpenOption.TRUNCATE_EXISTING).close();
            //BufferedWriter br=new BufferedWriter(fr);
            //PrintWriter pr=new PrintWriter(br);
            int has=0;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                if(has==1)
                {
                    history=history+str+";";
                }
                if(str.contains(newdashapp.topic))
                {
                    has=1;
                }
                pr.println(str);
            }
            if(has==0)
            {
                pr.println("1#"+newdashapp.topic);
            }
            pr.println(tas);
            history=history+tas+";";
            pr.close();
            br.close();
            fw.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        // Define the directory and file names
        String programDirectoryName = ".DASHAPP";
        String fileName = "tasksave.txt";

        // Get the user's home directory
        String userHome = System.getProperty("user.home");

        // Construct the full path to the .DASHAPP directory
        Path programDirectory = Paths.get(userHome, programDirectoryName);

        // Create the .DASHAPP directory if it doesn't exist
        try {
            Files.createDirectories(programDirectory);
            System.out.println("Directory created: " + programDirectory.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to create directory: " + programDirectory.toAbsolutePath());
            e.printStackTrace();
            return; // Exit the program if directory creation fails
        }

        // Construct the full path to the file inside the .DASHAPP directory
        Path file = programDirectory.resolve(fileName);
        
        String newfileName="temp.txt";

        // Define the path to the new file
        Path newFilePath = programDirectory.resolve(newfileName);

        // Read content from the new file
        try {
            byte[] newFileContent = Files.readAllBytes(newFilePath);

            // Write content to the old file, overwriting its previous contents
            Files.write(file, newFileContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("File replaced successfully.");
        } catch (IOException e) {
            System.err.println("Failed to replace file: " + file.toAbsolutePath());
            e.printStackTrace();
        }

        
        HistoryPanel HistoryPanel=new HistoryPanel();

        //newdashapp.HistoryPanel=new HistoryPanel();

        //int index = newdashapp.tabbedPane.indexOfComponent(newdashapp.HistoryPanel);

        int index=newdashapp.tabbedPane.indexOfTab("Task Archive");
        newdashapp.tabbedPane.remove(index);
        newdashapp.tabbedPane.addTab("Task Archive", HistoryPanel);

        /*int index = newdashapp.tabbedPane.indexOfComponent(newdashapp.HistoryPanel);
        if (index != -1) {
        newdashapp.tabbedPane.remove(index);
        }

        newdashapp.tabbedPane.addTab("Page 3", Historypanel);*/


    }

    public static void initfile() throws IOException
    {
        String programDirectoryName = ".DASHAPP";
        String fileName = "tasksave.txt";

        // Get the user's home directory
        String userHome = System.getProperty("user.home");

        // Construct the full path to the .myProgram directory
        File programDirectory = new File(userHome, programDirectoryName);

        // Create the .myProgram directory if it doesn't exist
        if (!programDirectory.exists()) {
            boolean created = programDirectory.mkdirs(); // Creates the directory and any necessary parent directories
            if (created) {
                System.out.println("Directory created: " + programDirectory.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory: " + programDirectory.getAbsolutePath());
            }
        }

        // Construct the full path to the file inside the .myProgram directory
        File file = new File(programDirectory, fileName);

       
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, false); // true for append mode, false for truncate
        } catch (IOException e) {
            System.err.println("Error creating FileWriter: " + e.getMessage());
        }

      
        BufferedWriter br = new BufferedWriter(fw);
        PrintWriter pr = new PrintWriter(br);

        //FileWriter fr=new FileWriter("tasksave.txt",true);
        //Path pathObject = Paths.get("tasksave.txt");
        //Files.newOutputStream(pathObject, StandardOpenOption.TRUNCATE_EXISTING).close();
        //BufferedWriter br=new BufferedWriter(fr);
        //PrintWriter pr=new PrintWriter(br);

        try {
            String zapierUrl = "https://eom7cl2rbq4cxuy.m.pipedream.net"; // webhook URL

            URL url = new URL(zapierUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonData = "{ \"name\":\"" + newdashapp.name + "\" }";
            //String jsonData = "{ \"name\":\"" + "Pranav Gowrish" + "\" }";
            //String jsonData = "{ \"name\":\""+name+"\", "+ "\"artist\": \"" + value2+"\" }";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            //System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response data
                Map<String, java.util.List<String>> headerFields = connection.getHeaderFields();
                //START
                /*Path pathObject = Paths.get("usersave.txt");
                Files.newOutputStream(pathObject, StandardOpenOption.TRUNCATE_EXISTING).close();
                FileWriter fr=new FileWriter("usersave.txt",true);
                BufferedWriter br=new BufferedWriter(fr);
                PrintWriter pr=new PrintWriter(br);
                pr.println(name);*/
                //END
                for (Map.Entry<String, java.util.List<String>> entry : headerFields.entrySet()) {
                    String key = entry.getKey();
                    java.util.List<String> values = entry.getValue();
                    System.out.println(key + ": " + values);

                    if(key!=null)
                    {
                        if(key.equals("Data"))
                        {
                            String Data[] = values.toArray(new String[0]);
                            String Datarev[]=new String[Data.length];
                            //int ij=0;
                            for(int i=0;i<Data.length;i++)
                            {
                                Datarev[i]=Data[i];
                                //ij++;
                            }
                            for (int i=0;i<Datarev.length;i++) 
                            {
                                System.out.println("D="+Datarev[i]);
                                if(i%2==0)
                                {
                                    pr.println("1#"+Datarev[i]);
                                }
                                else
                                {
                                    String srt=Datarev[i];
                                    String word="";
                                    char ch=' ';
                                    for(int j=0;j<srt.length();j++)
                                    {
                                        ch=srt.charAt(j);//Video made;Blog uploaded;Research done;
                                        if(ch==';')
                                        {
                                            pr.println(word);
                                            word="";
                                        }
                                        else
                                            word+=ch;
                                    }
                                }
                            }

                        }

                    }

                }
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    System.out.println("Response Body: " + response.toString());
                    //respo=response.toString();
                }
            }

            connection.disconnect();

        } catch (Exception f) {
            f.printStackTrace();
        }

        pr.close();
        br.close();
        fw.close();

        HistoryPanel HistoryPanel=new HistoryPanel();
        //newdashapp.HistoryPanel=new HistoryPanel();
        //int index = newdashapp.tabbedPane.indexOfComponent(newdashapp.HistoryPanel);
        int index=newdashapp.tabbedPane.indexOfTab("Task Archive");
        newdashapp.tabbedPane.remove(index);
        newdashapp.tabbedPane.addTab("Task Archive", HistoryPanel);
    }

    public static void main(String[] args) {
        // JFrame to hold the JPanel
        try {
            // Windows Look and Feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Game Rules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500); 

       
        HistoryPanel panel = new HistoryPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}
