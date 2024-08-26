import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.time.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.nio.file.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;  
import javax.swing.*;  
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
//import com.formdev.flatlaf.FlatLightLaf;
import static javax.swing.JOptionPane.showMessageDialog;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class newdashapp extends JFrame {
    static JPanel notificationPanel = new JPanel();
    static JLabel notificationLabel = new JLabel();
    static String name;
    static JLabel us4;
    static JLabel us5;
    static JLabel pictureLabel;
    static String topic;
    static JTabbedPane tabbedPane;
    static HistoryPanel HistoryPanel;
    static int adminv=0;
    static String roles;
    public newdashapp(String namee) {
        try {
            // Windows Look and Feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("The DASH Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        name=namee;
        ImageIcon im = new ImageIcon(LoginBox.class.getResource("/logo.png"));
        setIconImage(im.getImage());
        //JPanel notificationPanel = new JPanel();

        //NOTIFICATION SYSTEM
        /*JLabel notificationLabel = new JLabel("Task Successfully Marked Finished");
        notificationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notificationPanel.add(notificationLabel, BorderLayout.CENTER);
        javax.swing.Timer timer = new javax.swing.Timer(3000, e -> {
        notificationPanel.setVisible(false);
        repaint(); // Refresh frame to reflect changes
        });
        timer.setRepeats(false); // Only fire once
        timer.start();*/
        //NOTIF END

        // Create split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(0.2); 

        // Top panel with pic
        JPanel topPanel = new JPanel();
        pictureLabel = new JLabel(new ImageIcon(LoginBox.class.getResource("/user.png"))); //top pic
        topPanel.add(pictureLabel);
        splitPane.setTopComponent(topPanel);

        JButton ig=new JButton();
        ig.setBounds(1010,11,51,51);
        ig.putClientProperty( "JButton.buttonType", "roundRect" );
        ig.setBackground(new Color(0, 0, 0, 0));
        ig.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ig.setContentAreaFilled(false);
        ig.setBorderPainted(false);
        ig.setOpaque(false); 
        ig.addActionListener(new ActionListener()
            {  
                public void actionPerformed(ActionEvent e)
                {  
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.instagram.com/the_dash_project/"));
                    } catch (IOException | URISyntaxException ef) {
                        ef.printStackTrace();
                    }
                }
            });

        pictureLabel.add(ig);

        JButton site=new JButton();
        site.setBounds(1010,75,51,51);
        site.putClientProperty( "JButton.buttonType", "roundRect" );
        site.setBackground(new Color(0, 0, 0, 0));
        site.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        site.setContentAreaFilled(false);
        site.setBorderPainted(false);
        site.setOpaque(false); 
        site.addActionListener(new ActionListener()
            {  
                public void actionPerformed(ActionEvent e)
                {  
                    try {
                        Desktop.getDesktop().browse(new URI("https://thedashprojectteam.wixsite.com/thedashproject/dash-calendar"));
                    } catch (IOException | URISyntaxException ef) {
                        ef.printStackTrace();
                    }
                }
            });

        pictureLabel.add(site);

        JButton set=new JButton();
        set.setBounds(1010,138,51,51);
        set.putClientProperty( "JButton.buttonType", "roundRect" );
        set.setBackground(new Color(0, 0, 0, 0));
        set.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        set.setContentAreaFilled(false);
        set.setBorderPainted(false);
        set.setOpaque(false); 
        set.addActionListener(new ActionListener()
            {  
                public void actionPerformed(ActionEvent e)
                {  
                    JFrame settings=new JFrame();
                    settings.setTitle("Settings");
                    settings.setSize(450, 400);
                    settings.setLocationRelativeTo(null);
                    settings.setVisible(true);
                    ImageIcon im = new ImageIcon(LoginBox.class.getResource("/logo.png"));
                    settings.setIconImage(im.getImage());

                    JPanel mainPanel = new JPanel(new BorderLayout());
                    settings.add(mainPanel);

                    JPanel cards;
                    CardLayout cardLayout;
                    cards = new JPanel();
                    cardLayout = new CardLayout();
                    cards.setLayout(cardLayout);
                    mainPanel.add(cards, BorderLayout.CENTER);

                    JMenuBar menuBar = new JMenuBar();
                    mainPanel.add(menuBar, BorderLayout.NORTH); // Place the menu on the left side

                    JButton themeButton = new JButton("General");
                    JButton emailButton = new JButton("Updates");
                    JButton resetButton = new JButton("Feedback");
                    JButton aboutButton = new JButton("About");
                    JButton helpButton = new JButton("Issues?");

                    JPanel themePanel = new JPanel(new BorderLayout());
                    JPanel themeSelectionPanel = new JPanel();
                    themePanel.add(themeSelectionPanel, BorderLayout.CENTER);

                    JButton theme1 = new JButton("ReSync");
                    JLabel label1=new JLabel("Refreshes local data with latest data from DASH Database");
                    themeSelectionPanel.add(theme1);
                    themeSelectionPanel.add(label1);

                    theme1.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame load = new JFrame();
                                load.setUndecorated(true); // remove title bar
                                load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                load.setSize(400, 300);
                                load.setLocationRelativeTo(null); 
                                JPanel panel = new JPanel(new BorderLayout());
                                // Add loading GIF
                                ImageIcon loadingIcon = new ImageIcon(LoginBox.class.getResource("/loadsync.gif"));

                                ImageIcon finishload = new ImageIcon(LoginBox.class.getResource("/donesync.gif"));
                                JLabel loadingLabel = new JLabel(loadingIcon);
                                loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                panel.add(loadingLabel, BorderLayout.CENTER);
                                // Add text
                                JLabel textLabel = new JLabel("User data resyncing, this may take a moment..");
                                textLabel.setFont(new Font("Monospaced", Font.BOLD,12));
                                textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                panel.add(textLabel, BorderLayout.SOUTH);
                                load.add(panel);
                                load.setVisible(true);

                                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                                        @Override
                                        protected Void doInBackground() throws Exception {
                                            //TIME CONSUMING OPERATION
                                            try {
                                                String zapierUrl = "https://eo4f93vn1rpz82e.m.pipedream.net"; // webhook URL

                                                URL url = new URL(zapierUrl);
                                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                                connection.setRequestMethod("POST");
                                                connection.setRequestProperty("Content-Type", "application/json");
                                                connection.setDoOutput(true);

                                                String jsonData = "{ \"name\":\"" + name + "\" }";
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
                                                    String programDirectoryName = ".DASHAPP";
                                                    String fileName = "usersave.txt";

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
                                                        fw = new FileWriter(file, false); // true for append mode
                                                    } catch (IOException e) {
                                                        System.err.println("Error creating FileWriter: " + e.getMessage());
                                                    }

                                                    BufferedWriter br = new BufferedWriter(fw);
                                                    PrintWriter pr = new PrintWriter(br);

                                                    //Path pathObject = Paths.get("usersave.txt");
                                                    //Files.newOutputStream(pathObject, StandardOpenOption.TRUNCATE_EXISTING).close();
                                                    //FileWriter fr=new FileWriter("usersave.txt",true);
                                                    //BufferedWriter br=new BufferedWriter(fr);
                                                    //PrintWriter pr=new PrintWriter(br);
                                                    pr.println(name);
                                                    //END
                                                    for (Map.Entry<String, java.util.List<String>> entry : headerFields.entrySet()) {
                                                        String key = entry.getKey();
                                                        java.util.List<String> values = entry.getValue();
                                                        System.out.println(key + ": " + values);

                                                        if(key!=null)
                                                        {
                                                            if(key.equals("UData"))
                                                            {
                                                                String UData[] = values.toArray(new String[0]);
                                                                String revudata[]=new String[UData.length];
                                                                int ij=0;
                                                                for(int i=UData.length-1;i>=0;i--)
                                                                {
                                                                    revudata[ij]=UData[i];
                                                                    ij++;
                                                                }
                                                                for (String value : revudata) 
                                                                {
                                                                    System.out.println(value);
                                                                    pr.println(value);
                                                                }

                                                            }

                                                        }

                                                    }
                                                    pr.close();
                                                    br.close();
                                                    fw.close();
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
                                            String programDirectoryName1 = ".DASHAPP";
                                            String fileName1 = "usersave.txt";

                                            // Get the user's home directory
                                            String userHome1 = System.getProperty("user.home");

                                            File programDirectory1 = new File(userHome1, programDirectoryName1);

                                            if (!programDirectory1.exists()) {
                                                boolean created = programDirectory1.mkdirs(); // Creates the directory 
                                                if (created) {
                                                    System.out.println("Directory created: " + programDirectory1.getAbsolutePath());
                                                } else {
                                                    System.out.println("Failed to create directory: " + programDirectory1.getAbsolutePath());
                                                }
                                            }

                                            // Construct the full path to the file inside the .dash directory
                                            File file1 = new File(programDirectory1, fileName1);

                                            // Create the file if it doesn't exist
                                            if (!file1.exists()) {
                                                try {
                                                    boolean created = file1.createNewFile();
                                                    if (created) {
                                                        System.out.println("File created: " + file1.getAbsolutePath());
                                                    } else {
                                                        System.out.println("Failed to create file: " + file1.getAbsolutePath());
                                                    }
                                                } catch (IOException e) {
                                                    System.err.println("Error creating file: " + e.getMessage());
                                                }
                                            }


                                            Scanner sc1 = new Scanner(file1);

                                            //Scanner sc1 = new Scanner(new File("usersave.txt"));
                                            String nm=sc1.nextLine();
                                            roles=sc1.nextLine();
                                            String hrs=sc1.nextLine();
                                            String exp=sc1.nextLine();
                                            String mems=sc1.nextLine();
                                            if(roles.contains("Founder"))
                                            {
                                                adminv=1;
                                            }
                                            JLabel us1 = new JLabel(nm);
                                            us1.setBounds(220,25,300,50);
                                            us1.setFont(new Font("Georgia", Font.BOLD,24));
                                            us1.setForeground(new Color(255, 243, 243));
                                            JLabel us2 = new JLabel(roles);
                                            us2.setBounds(220,25,500,110);
                                            us2.setFont(new Font("Monospaced", Font.BOLD,13));
                                            us2.setForeground(new Color(255, 243, 243));
                                            JLabel us3;
                                            if(exp.equals("0"))
                                                us3 = new JLabel("Member since "+mems);
                                            else
                                                us3 = new JLabel("Volunteer since "+mems+" until "+exp);
                                            us3.setBounds(220,25,700,250);
                                            us3.setFont(new Font("Arial", Font.BOLD,15));
                                            us3.setForeground(new Color(255, 243, 243));
                                            us4 = new JLabel("Total Hours Spent: "+hrs);
                                            us4.setBounds(720,25,300,150);
                                            us4.setFont(new Font("Verdana", Font.BOLD,14));
                                            us4.setForeground(new Color(255, 243, 243));
                                            us5 = new JLabel("");//Current Topic
                                            us5.setBounds(450,10,700,30);
                                            us5.setFont(new Font("Verdana", Font.BOLD,13));
                                            us5.setForeground(new Color(255, 243, 243));
                                            pictureLabel.add(us1);
                                            pictureLabel.add(us2);
                                            pictureLabel.add(us3);
                                            pictureLabel.add(us4);
                                            pictureLabel.add(us5);
                                            sc1.close();
                                            if(adminv==1)
                                                GifPanel.buttonPanel.setVisible(true);
                                            pictureLabel.repaint();

                                            try
                                            {
                                                BarGraphPanel.initfile();
                                                HistoryPanel.initfile();
                                            }
                                            catch (IOException ioe)
                                            {
                                                ioe.printStackTrace();
                                            }
                                            //OPERATION END
                                            return null;
                                        }

                                        @Override
                                        protected void done() {
                                            loadingLabel.setIcon(finishload);
                                            javax.swing.Timer timer = new javax.swing.Timer(1500, e -> {
                                                            load.setVisible(false);
                                                    });
                                            timer.setRepeats(false);
                                            timer.start();
                                        }
                                    };
                                worker.execute();

                            }
                        });

                    menuBar.add(themeButton);
                    menuBar.add(emailButton);
                    menuBar.add(resetButton);
                    //menuBar.add(aboutButton);
                    //menuBar.add(helpButton);

                    JPanel emailPanel = new JPanel(new BorderLayout());
                    JPanel aboutSelectionPanel = new JPanel();
                    emailPanel.add(aboutSelectionPanel, BorderLayout.SOUTH);
                    JTextArea abinfo= new JTextArea("This Application's Version is below. Click on the button to check if you're \n running the latest version!");
                    abinfo.setFont(new Font("Tahoma",Font.BOLD,11));
                    abinfo.setOpaque(false);
                    abinfo.setEditable(false);
                    abinfo.setBackground(new Color(0, 0, 0, 0));
                    abinfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    JLabel abv=new JLabel("                 v.3.0 [10/4/24]");//UPDATE
                    abv.setFont(new Font("Monospaced",Font.BOLD,15));
                    abv.setForeground(Color.BLUE);
                    emailPanel.add(abv,BorderLayout.CENTER);
                    abv.setVisible(true);
                    emailPanel.add(abinfo,BorderLayout.NORTH);
                    JButton checkv=new JButton("Check Version Available");
                    checkv.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Desktop.getDesktop().browse(new URI("https://thedashprojectteam.wixsite.com/thedashproject/appupdate"));
                                } catch (IOException | URISyntaxException ef) {
                                    ef.printStackTrace();
                                }
                            }
                        });

                    emailPanel.add(checkv,BorderLayout.SOUTH);

                    cards.add(themePanel, "Theme");
                    cards.add(emailPanel, "Email");
                    //cards.add(resetPanel, "Reset");
                    //cards.add(aboutPanel, "About");
                    themeButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                cardLayout.show(cards, "Theme");
                            }
                        });

                    emailButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                cardLayout.show(cards, "Email");
                            }
                        });

                    resetButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String inp = JOptionPane.showInputDialog(settings, "Enter feedback:");

                                if (inp != null) {
                                    // Message Dialog box
                                    JOptionPane.showMessageDialog(settings, "Feedback Sent!");
                                    try {
                                        String zapierUrl = "https://eou4asxl0gf141m.m.pipedream.net"; // webhook URL

                                        URL url = new URL(zapierUrl);
                                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                        connection.setRequestMethod("POST");
                                        connection.setRequestProperty("Content-Type", "application/json");
                                        connection.setDoOutput(true);

                                        String jsonData = "{ \"fd\":\"" + inp + "\" }";
                                        //String jsonData = "{ \"name\":\""+name+"\", "+ "\"artist\": \"" + value2+"\" }";

                                        try (OutputStream os = connection.getOutputStream()) {
                                            byte[] input = jsonData.getBytes("utf-8");
                                            os.write(input, 0, input.length);
                                        }

                                        int responseCode = connection.getResponseCode();
                                        //System.out.println("Response Code: " + responseCode);

                                        if (responseCode == HttpURLConnection.HTTP_OK) {
                                            Map<String, java.util.List<String>> headerFields = connection.getHeaderFields();
                                            //START
                                            Path pathObject = Paths.get("usersave.txt");
                                            Files.newOutputStream(pathObject, StandardOpenOption.TRUNCATE_EXISTING).close();
                                            FileWriter fr=new FileWriter("usersave.txt",true);
                                            BufferedWriter br=new BufferedWriter(fr);
                                            PrintWriter pr=new PrintWriter(br);
                                            pr.println(name);
                                            //END
                                            for (Map.Entry<String, java.util.List<String>> entry : headerFields.entrySet()) {
                                                String key = entry.getKey();
                                                java.util.List<String> values = entry.getValue();
                                                System.out.println(key + ": " + values);

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
                                } else {
                                    
                                }

                            }
                        });
                    aboutButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                cardLayout.show(cards, "About");
                            }
                        });
                }
            });

        pictureLabel.add(set);

        notificationPanel.setBackground(new Color(32, 178, 170));
        
        notificationPanel.setBounds(0, 0, 500, 30); //left corner
        notificationPanel.setLayout(new BorderLayout());
        add(notificationPanel);
        notificationPanel.setVisible(false);

        //String name="Varun";//STARTAPP
        GifPanel GifPanel=new GifPanel();

        LocalTime currentTime = LocalTime.now(); 
        if (currentTime.isBefore(LocalTime.NOON)) {
            notif("Good Morning "+name);
        } else if (currentTime.isBefore(LocalTime.of(17, 0))) {
            notif("Good Afternoon "+name);
        } else if (currentTime.isBefore(LocalTime.of(20, 0))) {
            notif("Good Evening "+name);
        } else {
            notif("Good Night "+name);
        }
        try {
            String programDirectoryName = ".DASHAPP";
            String fileName = "usersave.txt";

            // Get the user's home directory
            String userHome = System.getProperty("user.home");

    
            File programDirectory = new File(userHome, programDirectoryName);

        
            if (!programDirectory.exists()) {
                boolean created = programDirectory.mkdirs(); 
                if (created) {
                    System.out.println("Directory created: " + programDirectory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + programDirectory.getAbsolutePath());
                }
            }

            File file = new File(programDirectory, fileName);

        
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

            //File file = new File("usersave.txt");
            //Scanner sc = new Scanner(file);
            String nm="none";
            if(file.exists() && file.length() > 0)
                nm=sc.nextLine();

            if(nm.equals(name))
            {
                roles=sc.nextLine();
                String hrs=sc.nextLine();
                String exp=sc.nextLine();
                String mems=sc.nextLine();

                if(roles.contains("Founder"))
                {
                    adminv=1;
                }

                JLabel us1 = new JLabel(nm);
                us1.setBounds(220,25,300,50);
                us1.setFont(new Font("Georgia", Font.BOLD,24));
                us1.setForeground(new Color(255, 243, 243));
                JLabel us2 = new JLabel(roles);
                us2.setBounds(220,25,500,110);
                us2.setFont(new Font("Monospaced", Font.BOLD,13));
                us2.setForeground(new Color(255, 243, 243));
                JLabel us3;
                if(exp.equals("0"))
                    us3 = new JLabel("Member since "+mems);
                else
                    us3 = new JLabel("Volunteer since "+mems+" until "+exp);
                us3.setBounds(220,25,700,250);
                us3.setFont(new Font("Ariel", Font.BOLD,15));
                us3.setForeground(new Color(255, 243, 243));
                /*JLabel us5 = new JLabel("Member since "+mems);
                us5.setBounds(220,25,300,250);
                us5.setFont(new Font("Verdana", Font.BOLD,15));
                us5.setForeground(new Color(255, 243, 243));*/
                us4 = new JLabel("Total Hours Spent: "+hrs);
                us4.setBounds(720,25,300,150);
                us4.setFont(new Font("Verdana", Font.BOLD,14));
                us4.setForeground(new Color(255, 243, 243));
                us5 = new JLabel("");//Current Topic
                us5.setBounds(450,10,700,30);
                us5.setFont(new Font("Verdana", Font.BOLD,13));
                us5.setForeground(new Color(255, 243, 243));
                pictureLabel.add(us1);
                pictureLabel.add(us2);
                pictureLabel.add(us3);
                pictureLabel.add(us4);
                pictureLabel.add(us5);
                sc.close();
                if(adminv==1)
                    GifPanel.buttonPanel.setVisible(true);
            }
            else
            {
                //syncgif
                JFrame load = new JFrame();
                load.setUndecorated(true); // Removes the title bar
                load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                load.setSize(400, 300);
                load.setLocationRelativeTo(null); // Centers the frame on screen
                load.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowActivated(WindowEvent e) {
                            setAlwaysOnTop(false); // Allow otherFrame to come in front
                            load.setAlwaysOnTop(true); // Keep otherFrame on top
                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {
                            load.setAlwaysOnTop(false); // Release always on top when not in focus
                        }
                    });
                JPanel panel = new JPanel(new BorderLayout());
                // Add loading GIF
                ImageIcon loadingIcon = new ImageIcon(LoginBox.class.getResource("/loadsync.gif"));

                ImageIcon finishload = new ImageIcon(LoginBox.class.getResource("/donesync.gif"));
                JLabel loadingLabel = new JLabel(loadingIcon);
                loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(loadingLabel, BorderLayout.CENTER);
                // Add text
                JLabel textLabel = new JLabel("User data syncing locally, this may take a moment..");
                textLabel.setFont(new Font("Monospaced", Font.BOLD,12));
                textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(textLabel, BorderLayout.SOUTH);
                load.add(panel);
                load.setVisible(true);

                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            //TIME CONSUMING OPERATION
                            try {
                                String zapierUrl = "https://eo4f93vn1rpz82e.m.pipedream.net"; //webhook URL

                                URL url = new URL(zapierUrl);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("POST");
                                connection.setRequestProperty("Content-Type", "application/json");
                                connection.setDoOutput(true);

                                String jsonData = "{ \"name\":\"" + name + "\" }";
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
                                    String programDirectoryName = ".DASHAPP";
                                    String fileName = "usersave.txt";

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
                                        fw = new FileWriter(file, false); // true for append mode
                                    } catch (IOException e) {
                                        System.err.println("Error creating FileWriter: " + e.getMessage());
                                    }

                                    // Create the BufferedWriter and PrintWriter
                                    BufferedWriter br = new BufferedWriter(fw);
                                    PrintWriter pr = new PrintWriter(br);

                                    //Path pathObject = Paths.get("usersave.txt");
                                    //Files.newOutputStream(pathObject, StandardOpenOption.TRUNCATE_EXISTING).close();
                                    //FileWriter fr=new FileWriter("usersave.txt",true);
                                    //BufferedWriter br=new BufferedWriter(fr);
                                    //PrintWriter pr=new PrintWriter(br);
                                    pr.println(name);
                                    //END
                                    for (Map.Entry<String, java.util.List<String>> entry : headerFields.entrySet()) {
                                        String key = entry.getKey();
                                        java.util.List<String> values = entry.getValue();
                                        System.out.println(key + ": " + values);

                                        if(key!=null)
                                        {
                                            if(key.equals("UData"))
                                            {
                                                String UData[] = values.toArray(new String[0]);
                                                String revudata[]=new String[UData.length];
                                                int ij=0;
                                                for(int i=UData.length-1;i>=0;i--)
                                                {
                                                    revudata[ij]=UData[i];
                                                    ij++;
                                                }
                                                for (String value : revudata) 
                                                {
                                                    System.out.println(value);
                                                    pr.println(value);
                                                }

                                            }

                                        }

                                    }
                                    pr.close();
                                    br.close();
                                    fw.close();
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
                            String programDirectoryName1 = ".DASHAPP";
                            String fileName1 = "usersave.txt";

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

                            // Create the file if it doesn't exist
                            if (!file1.exists()) {
                                try {
                                    boolean created = file1.createNewFile();
                                    if (created) {
                                        System.out.println("File created: " + file1.getAbsolutePath());
                                    } else {
                                        System.out.println("Failed to create file: " + file1.getAbsolutePath());
                                    }
                                } catch (IOException e) {
                                    System.err.println("Error creating file: " + e.getMessage());
                                }
                            }

                            
                            Scanner sc1 = new Scanner(file1);

                            //Scanner sc1 = new Scanner(new File("usersave.txt"));
                            String nm=sc1.nextLine();
                            roles=sc1.nextLine();
                            String hrs=sc1.nextLine();
                            String exp=sc1.nextLine();
                            String mems=sc1.nextLine();
                            if(roles.contains("Founder"))
                            {
                                adminv=1;
                            }
                            JLabel us1 = new JLabel(nm);
                            us1.setBounds(220,25,300,50);
                            us1.setFont(new Font("Georgia", Font.BOLD,24));
                            us1.setForeground(new Color(255, 243, 243));
                            JLabel us2 = new JLabel(roles);
                            us2.setBounds(220,25,500,110);
                            us2.setFont(new Font("Monospaced", Font.BOLD,13));
                            us2.setForeground(new Color(255, 243, 243));
                            JLabel us3;
                            if(exp.equals("0"))
                                us3 = new JLabel("Member since "+mems);
                            else
                                us3 = new JLabel("Volunteer since "+mems+" until "+exp);
                            us3.setBounds(220,25,700,250);
                            us3.setFont(new Font("Arial", Font.BOLD,15));
                            us3.setForeground(new Color(255, 243, 243));
                            us4 = new JLabel("Total Hours Spent: "+hrs);
                            us4.setBounds(720,25,300,150);
                            us4.setFont(new Font("Verdana", Font.BOLD,14));
                            us4.setForeground(new Color(255, 243, 243));
                            us5 = new JLabel("");//Current Topic
                            us5.setBounds(450,10,700,30);
                            us5.setFont(new Font("Verdana", Font.BOLD,13));
                            us5.setForeground(new Color(255, 243, 243));
                            pictureLabel.add(us1);
                            pictureLabel.add(us2);
                            pictureLabel.add(us3);
                            pictureLabel.add(us4);
                            pictureLabel.add(us5);
                            sc1.close();
                            if(adminv==1)
                                GifPanel.buttonPanel.setVisible(true);
                            pictureLabel.repaint();

                            BarGraphPanel.initfile();
                            HistoryPanel.initfile();
                            //OPERATION END
                            return null;
                        }

                        @Override
                        protected void done() {
                            loadingLabel.setIcon(finishload);
                            javax.swing.Timer timer = new javax.swing.Timer(1500, e -> {
                                            load.setVisible(false);
                                    });
                            timer.setRepeats(false);
                            timer.start();
                        }
                    };
                worker.execute();

                //syncgifdone 
                //syncgifdone ends
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        //END STARTAPP
        // Bottom panel with tabs
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        //tabbedPane.setBackground(Color.yellow);
        //tabbedPane.setForeground(Color.black);
        //GifPanel GifPanel=new GifPanel();
        tabbedPane.addTab("Home",GifPanel);

        // Page 1 with buttons
        TaskPanel TaskPanel = new TaskPanel();
        /*JPanel page1 = new JPanel();
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        page1.add(button1);
        page1.add(button2);
        page1.add(button3);*/
        tabbedPane.addTab("My Tasks", TaskPanel);

        int nc = 0;
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

            // Now use the file for reading and writing operations

            // Create the Scanner
            Scanner sc = new Scanner(file);

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
       
        JPanel barpanel = new JPanel(new BorderLayout());
        BarGraphPanel BarGraphPanel = new BarGraphPanel(data);
        JScrollPane scrollPane = new JScrollPane(BarGraphPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        barpanel.add(scrollPane, BorderLayout.CENTER);

        /*JPanel page2 = new JPanel();
        JTextField textField1 = new JTextField(20);
        JTextField textField2 = new JTextField(20);
        page2.add(new JLabel("Text Field 1:"));
        page2.add(textField1);
        page2.add(new JLabel("Text Field 2:"));
        page2.add(textField2);*/
        tabbedPane.addTab("Hours at a Glance", barpanel);
        tabbedPane.addChangeListener(e -> {
                    // Get the index of the selected tab
                    int selectedIndex = tabbedPane.getSelectedIndex();
                    // Get the title of the selected tab
                    String selectedTitle = tabbedPane.getTitleAt(selectedIndex);
                    // Check if the title matches "Hours at a Glance"
                    if ("Hours at a Glance".equals(selectedTitle)) {
                        // Get the component of the selected tab
                        BarGraphPanel.startAnimation();
                    }
            });

      
        //JPanel page3 = new JPanel();
        HistoryPanel = new HistoryPanel();
        tabbedPane.addTab("Task Archive", HistoryPanel);

        // Customize tabbed pane appearance
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        //tabbedPane.setFont(new Font("Arial", Font.BOLD, 16)); // Set tab font size
        /*for (int i = 0; i < tabbedPane.getTabCount(); i++) {
        tabbedPane.setTabComponentAt(i, new CustomTabLabel(tabbedPane.getTitleAt(i))); // Set custom tab rendering
        }*/
        //tabbedPane.setOpaque(true);

        //tabbedPane.setBackgroundAt(1, Color.RED);

        bottomPanel.add(tabbedPane, BorderLayout.CENTER);
        splitPane.setBottomComponent(bottomPanel);

        getContentPane().add(splitPane);
    }

    public static void notif(String notif)
    {
        //NOTIFICATION SYSTEM
        notificationPanel.setVisible(true);
        notificationLabel.setText(notif);
        notificationLabel.setForeground(new Color(0, 89, 84));
        notificationLabel.setFont(new Font("Georgia", Font.PLAIN,15));
        notificationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notificationPanel.add(notificationLabel, BorderLayout.CENTER);
        javax.swing.Timer timer = new javax.swing.Timer(4000, e -> {
                        notificationPanel.setVisible(false);
                        //repaint(); // Refresh frame to reflect changes
                });
        timer.setRepeats(false); // Only fire once
        timer.start();
        //NOTIF END
    }

    private class CustomTabLabel extends JLabel {
        public CustomTabLabel(String text) {
            super(text);
            setHorizontalAlignment(JLabel.CENTER);
            setPreferredSize(new Dimension(200, 50)); //tab size
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                    newdashapp frame = new newdashapp("Test User");
                    frame.setVisible(true);
            });
    }
}
