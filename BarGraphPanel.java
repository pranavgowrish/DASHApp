import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.nio.file.*;
import java.awt.*;
import java.net.*;
import java.nio.file.*;

public class BarGraphPanel extends JPanel {
    private Map<String, Double> data;
    private javax.swing.Timer timer;
    private int animationDuration = 1000; // Animation duration in milliseconds
    private long startTime;
    private static final int PANEL_WIDTH = 500; // Set constant width
    private static final int PANEL_HEIGHT = 360; // Set constant height
    private static final int MAX_BAR_HEIGHT = 250; // Set constant max height for bars

    public BarGraphPanel(Map<String, Double> data) {
        this.data = data;
        startAnimation();
        addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    startAnimation();
                }
            });
        setBackground(new Color(39, 60, 125));
    }

    public void startAnimation() {
        startTime = System.currentTimeMillis();
        timer = new javax.swing.Timer(10, e -> {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    if (elapsedTime >= animationDuration) {
                        ((javax.swing.Timer) e.getSource()).stop();
                    }
                    repaint();
            });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double maxValue = getMaxValue();

        int barWidth = 50;
        int gap = 60;
        int startX = 50;
        int startY = PANEL_HEIGHT - 50; 
        int maxValueY = startY - 20;

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        float progress = (float) elapsedTime / animationDuration;

        int x = startX;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            String topic = entry.getKey();
            double hours = entry.getValue();
            int barHeight = (int) (hours * MAX_BAR_HEIGHT / maxValue * Math.min(progress, 1)); // Adjusted calculation

            // Draw curved bar
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.BLACK);
            g2d.fillRoundRect(x, startY - barHeight, barWidth, barHeight, 20, 20);
            g2d.dispose();

            // Draw topic label
            g.setColor(new Color(153, 153, 153));
            Font font = new Font("SansSerif", Font.BOLD, 10);
            g.setFont(font);
            g.drawString(topic, x + barWidth / 2 - g.getFontMetrics().stringWidth(topic) / 2, startY + 20);

            Font font1 = new Font("Georgia", Font.BOLD, 13);
            g.setFont(font1);

            // Draw hours label
            g.drawString(Double.toString(hours), x + barWidth / 2 - g.getFontMetrics().stringWidth(Double.toString(hours)) / 2, startY - barHeight - 10);

            x += (barWidth + gap);
        }

        // Draw Y-axis
        g.drawLine(startX, startY, startX, 50);
        // Draw X-axis
        g.drawLine(startX, startY, getWidth() - 50, startY); // Adjusted to use width of the panel

        // Draw Y-axis label with some distance
        g.drawString("Hours", 2, (startY + 50) / 2);

        // Draw X-axis label with some distance
        g.drawString("Topics", (getWidth() + startX - 50) / 2, startY + 40); // Adjusted to use width of the panel

    }

    @Override
    public Dimension getPreferredSize() {
        int totalWidth = (data.size() * 50) + ((data.size() - 1) * 60) + 100; // Total width of bars + gaps + margins
        return new Dimension(totalWidth, PANEL_HEIGHT); 
    }

    private double getMaxValue() {
        double maxValue = 0;
        for (double value : data.values()) {
            if (value > maxValue)
                maxValue = value;
        }
        return maxValue;
    }
    static double sv;
    public static void add(double hor) throws IOException
    {
        try {
            String programDirectoryName = ".DASHAPP";
            String fileName = "hoursave.txt";

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

         
            FileWriter fw = null;
            try {
                fw = new FileWriter(file1, false); // true for append mode
            } catch (IOException e) {
                System.err.println("Error creating FileWriter: " + e.getMessage());
            }

  
            BufferedWriter br = new BufferedWriter(fw);
            PrintWriter pr = new PrintWriter(br);

            //Scanner sc = new Scanner(new File("hoursave.txt"));
            //FileWriter fr=new FileWriter("temp.txt",true);
            //Path pathObject = Paths.get("temp.txt");
            //Files.newOutputStream(pathObject, StandardOpenOption.TRUNCATE_EXISTING).close();
            //BufferedWriter br=new BufferedWriter(fr);
            //PrintWriter pr=new PrintWriter(br);
            int has=0;
            String hs="";
            while (sc.hasNext()) {
                String str = sc.nextLine();
                if(str.contains(newdashapp.topic))
                {
                    has=1;
                    hs=str;
                    break;
                }
                pr.println(str);
            }
            if(has==0)
            {
                sv=hor;
                pr.println(newdashapp.topic+"$"+hor);
            }
            else
            {
                String sh=hs.substring(hs.indexOf('$')+1);
                sv=Double.valueOf(sh);
                sv+=hor;
                pr.println(newdashapp.topic+"$"+sv);
            }
            pr.close();
            br.close();
            fw.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        // Define the directory and file names
        String programDirectoryName = ".DASHAPP";
        String fileName = "hoursave.txt";

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

        // Define the path to the new file
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
        
        

        //HistoryPanel HistoryPanel=new HistoryPanel();
        int nc = 0;
        try {
            String programDirectoryName2 = ".DASHAPP";
            String fileName2 = "hoursave.txt";

            // Get the user's home directory
            String userHome2 = System.getProperty("user.home");

            // Construct the full path to the .myProgram directory
            File programDirectory2 = new File(userHome2, programDirectoryName2);

            // Create the .myProgram directory if it doesn't exist
            if (!programDirectory2.exists()) {
                boolean created = programDirectory2.mkdirs(); // Creates the directory and any necessary parent directories
                if (created) {
                    System.out.println("Directory created: " + programDirectory2.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + programDirectory2.getAbsolutePath());
                }
            }

            // Construct the full path to the file inside the .myProgram directory
            File file2 = new File(programDirectory2, fileName2);

            // Create the file if it doesn't exist
            if (!file2.exists()) {
                try {
                    boolean created = file2.createNewFile();
                    if (created) {
                        System.out.println("File created: " + file2.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create file: " + file2.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.err.println("Error creating file: " + e.getMessage());
                }
            }

            
            Scanner sc = new Scanner(file2);
            
            //Scanner sc = new Scanner(new File("hoursave.txt"));
            while (sc.hasNext()) {
                String clr = sc.nextLine();
                nc++;
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        String topic[] = new String[nc];
        double hr[] = new double[nc];
        int nr = 0;
        try {
            String programDirectoryName3 = ".DASHAPP";
            String fileName3 = "hoursave.txt";

            // Get the user's home directory
            String userHome3 = System.getProperty("user.home");

            // Construct the full path to the .myProgram directory
            File programDirectory3 = new File(userHome3, programDirectoryName3);

            // Create the .myProgram directory if it doesn't exist
            if (!programDirectory3.exists()) {
                boolean created = programDirectory3.mkdirs(); // Creates the directory and any necessary parent directories
                if (created) {
                    System.out.println("Directory created: " + programDirectory3.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + programDirectory3.getAbsolutePath());
                }
            }

            // Construct the full path to the file inside the .myProgram directory
            File file3 = new File(programDirectory3, fileName3);

            // Create the file if it doesn't exist
            if (!file3.exists()) {
                try {
                    boolean created = file3.createNewFile();
                    if (created) {
                        System.out.println("File created: " + file3.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create file: " + file3.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.err.println("Error creating file: " + e.getMessage());
                }
            }

          
            Scanner sc = new Scanner(file3);
            
            //Scanner sc = new Scanner(new File("hoursave.txt"));
            while (sc.hasNext()) {
                String str = sc.nextLine();
                topic[nr] = str.substring(0, str.indexOf('$'));
                hr[nr] = Double.parseDouble(str.substring(str.indexOf('$') + 1));
                nr++;
            }
            for (int i = 0; i < nc; i++) {
                System.out.println(topic[i]);
                System.out.println(hr[i]);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        Map<String, Double> data = new LinkedHashMap<>();

        for (int i = 0; i < nc; i++) {
            data.put(topic[i], hr[i]);
        }
        BarGraphPanel BarGraphPanel=new BarGraphPanel(data);

        //newdashapp.HistoryPanel=new HistoryPanel();

        //int index = newdashapp.tabbedPane.indexOfComponent(newdashapp.HistoryPanel);
        JPanel barpanel = new JPanel(new BorderLayout());
        //BarGraphPanel BarGraphPanel = new BarGraphPanel(data);
        JScrollPane scrollPane = new JScrollPane(BarGraphPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        barpanel.add(scrollPane, BorderLayout.CENTER);

        int index=newdashapp.tabbedPane.indexOfTab("Hours at a Glance");
        newdashapp.tabbedPane.remove(index);
        newdashapp.tabbedPane.addTab("Hours at a Glance", barpanel);

        newdashapp.tabbedPane.addChangeListener(e -> {
                    // Get the index of the selected tab
                    int selectedIndex = newdashapp.tabbedPane.getSelectedIndex();
                    // Get the title of the selected tab
                    String selectedTitle = newdashapp.tabbedPane.getTitleAt(selectedIndex);
                    // Check if the title matches "Hours at a Glance"
                    if ("Hours at a Glance".equals(selectedTitle)) {
                        // Get the component of the selected tab
                        BarGraphPanel.startAnimation();
                    }
            });

    }

    public static void initfile() throws IOException
    {
        String programDirectoryName = ".DASHAPP";
        String fileName = "hoursave.txt";

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

        // Create the FileWriter
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, false); // true for append mode, false for truncate
        } catch (IOException e) {
            System.err.println("Error creating FileWriter: " + e.getMessage());
        }

        BufferedWriter br = new BufferedWriter(fw);
        PrintWriter pr = new PrintWriter(br);
        
    

        String Toprev[]={};
        String Valrev[]={};

        try {
            String zapierUrl = "https://eo3m579fjvplji5.m.pipedream.net"; // Replace with your Zapier webhook URL

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
                        if(key.equals("Val"))
                        {
                            String Val[] = values.toArray(new String[0]);
                            Valrev=new String[Val.length];
                            //int ij=0;
                            for(int i=0;i<Val.length;i++)
                            {
                                Valrev[i]=Val[i];
                                //ij++;
                            }
                        }
                        if(key.equals("Top"))
                        {
                            String Top[] = values.toArray(new String[0]);
                            Toprev=new String[Top.length];
                            //int ij=0;
                            for(int i=0;i<Top.length;i++)
                            {
                                Toprev[i]=Top[i];
                                //ij++;
                            }

                            /*for (int i=0;i<Datarev.length;i++) 
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
                            }*/

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

        //System.out.println("Values="+Toprev[2]);
        //System.out.println(Valrev[2]);
        for(int i=0;i<Toprev.length;i++)
        {
            pr.println(Toprev[i]+"$"+Valrev[i]);
        }

        pr.close();
        br.close();
        fw.close();

        //HistoryPanel HistoryPanel=new HistoryPanel();
        int nc = 0;
        try {
            String programDirectoryName2 = ".DASHAPP";
            String fileName2 = "hoursave.txt";

            // Get the user's home directory
            String userHome2 = System.getProperty("user.home");

            // Construct the full path to the .myProgram directory
            File programDirectory2 = new File(userHome2, programDirectoryName2);

            // Create the .myProgram directory if it doesn't exist
            if (!programDirectory2.exists()) {
                boolean created = programDirectory2.mkdirs(); // Creates the directory and any necessary parent directories
                if (created) {
                    System.out.println("Directory created: " + programDirectory2.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + programDirectory2.getAbsolutePath());
                }
            }

            // Construct the full path to the file inside the .myProgram directory
            File file2 = new File(programDirectory2, fileName2);

            // Create the file if it doesn't exist
            if (!file2.exists()) {
                try {
                    boolean created = file2.createNewFile();
                    if (created) {
                        System.out.println("File created: " + file2.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create file: " + file2.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.err.println("Error creating file: " + e.getMessage());
                }
            }

            
            Scanner sc = new Scanner(file2);
            
            //Scanner sc = new Scanner(new File("hoursave.txt"));
            while (sc.hasNext()) {
                String clr = sc.nextLine();
                nc++;
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        String topic[] = new String[nc];
        double hr[] = new double[nc];
        int nr = 0;
        try {
            String programDirectoryName3 = ".DASHAPP";
            String fileName3 = "hoursave.txt";

            // Get the user's home directory
            String userHome3 = System.getProperty("user.home");

            // Construct the full path to the .myProgram directory
            File programDirectory3 = new File(userHome3, programDirectoryName3);

            // Create the .myProgram directory if it doesn't exist
            if (!programDirectory3.exists()) {
                boolean created = programDirectory3.mkdirs(); // Creates the directory and any necessary parent directories
                if (created) {
                    System.out.println("Directory created: " + programDirectory3.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + programDirectory3.getAbsolutePath());
                }
            }

            // Construct the full path to the file inside the .myProgram directory
            File file3 = new File(programDirectory3, fileName3);

            // Create the file if it doesn't exist
            if (!file3.exists()) {
                try {
                    boolean created = file3.createNewFile();
                    if (created) {
                        System.out.println("File created: " + file3.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create file: " + file3.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.err.println("Error creating file: " + e.getMessage());
                }
            }

           
            Scanner sc = new Scanner(file3);
            
            //Scanner sc = new Scanner(new File("hoursave.txt"));
            while (sc.hasNext()) {
                String str = sc.nextLine();
                topic[nr] = str.substring(0, str.indexOf('$'));
                hr[nr] = Double.parseDouble(str.substring(str.indexOf('$') + 1));
                nr++;
            }
            for (int i = 0; i < nc; i++) {
                System.out.println(topic[i]);
                System.out.println(hr[i]);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        Map<String, Double> data = new LinkedHashMap<>();

        for (int i = 0; i < nc; i++) {
            data.put(topic[i], hr[i]);
        }
        BarGraphPanel BarGraphPanel=new BarGraphPanel(data);

        //newdashapp.HistoryPanel=new HistoryPanel();

        //int index = newdashapp.tabbedPane.indexOfComponent(newdashapp.HistoryPanel);
        JPanel barpanel = new JPanel(new BorderLayout());
        //BarGraphPanel BarGraphPanel = new BarGraphPanel(data);
        JScrollPane scrollPane = new JScrollPane(BarGraphPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        barpanel.add(scrollPane, BorderLayout.CENTER);

        int index=newdashapp.tabbedPane.indexOfTab("Hours at a Glance");
        newdashapp.tabbedPane.remove(index);
        newdashapp.tabbedPane.addTab("Hours at a Glance", barpanel);

        newdashapp.tabbedPane.addChangeListener(e -> {
                    // Get the index of the selected tab
                    int selectedIndex = newdashapp.tabbedPane.getSelectedIndex();
                    // Get the title of the selected tab
                    String selectedTitle = newdashapp.tabbedPane.getTitleAt(selectedIndex);
                    // Check if the title matches "Hours at a Glance"
                    if ("Hours at a Glance".equals(selectedTitle)) {
                        // Get the component of the selected tab
                        BarGraphPanel.startAnimation();
                    }
            });

    }

    public static void main(String[] args) {
        int nc = 0;//MAIN METHOD WILL nOT WORK
        try {
            Scanner sc = new Scanner(new File("hoursave.txt"));
            while (sc.hasNext()) {
                String clr = sc.nextLine();
                nc++;
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        String topic[] = new String[nc];
        double hr[] = new double[nc];
        int nr = 0;
        try {
            Scanner sc = new Scanner(new File("hoursave.txt"));
            while (sc.hasNext()) {
                String str = sc.nextLine();
                topic[nr] = str.substring(0, str.indexOf('$'));
                hr[nr] = Double.parseDouble(str.substring(str.indexOf('$') + 1));
                nr++;
            }
            for (int i = 0; i < nc; i++) {
                System.out.println(topic[i]);
                System.out.println(hr[i]);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        Map<String, Double> data = new LinkedHashMap<>();
        for (int i = 0; i < nc; i++) {
            data.put(topic[i], hr[i]);
        }

        // Create a JPanel to hold the BarGraphPanel
        JPanel panel = new JPanel(new BorderLayout());
        BarGraphPanel graph = new BarGraphPanel(data);
        JScrollPane scrollPane = new JScrollPane(graph, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);

        JFrame frame = new JFrame("Bar Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack(); // Packs the components

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
