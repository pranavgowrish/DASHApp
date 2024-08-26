import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.nio.file.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class LoginBox extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JToggleButton viewPasswordButton;

    public LoginBox() {
        try {
            // Windows Look and Feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Login");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        ImageIcon im = new ImageIcon(LoginBox.class.getResource("/logo.png"));
        setIconImage(im.getImage());

        // Panel for upper with pic
        JPanel upperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel imageLabel = new JLabel(new ImageIcon(LoginBox.class.getResource("/login.png"))); 
        upperPanel.add(imageLabel);

        // Panel for the lower for login
        JPanel lowerPanel = new JPanel(new GridLayout(4, 1));
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*'); 

        loginButton = new JButton("Login");
        viewPasswordButton = new JToggleButton("👁");//emoji lol

        loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    login();
                }
            });

        passwordField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        login();
                    }
                }
            });

        viewPasswordButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (viewPasswordButton.isSelected()) {
                        passwordField.setEchoChar((char) 0); // Show password
                    } else {
                        passwordField.setEchoChar('*'); // Hide password
                    }
                }
            });

        // Adding components to the lower panel
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        buttonPanel.add(loginButton);
        passwordPanel.add(viewPasswordButton);

        lowerPanel.add(new JLabel()); // Empty label for spacing
        lowerPanel.add(usernamePanel);
        lowerPanel.add(passwordPanel);
        lowerPanel.add(buttonPanel);

        // Adding upper and lower panels to the frame
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.CENTER);
    }

    static String[] users=new String[100];//UPDATE
    static String[] pass=new String[100];//UPDATE

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());


        note();
        boolean valid=false;
        int exists=0;
        for(int i=0;i<users.length;i++)
        {
            if(username.equals(users[i]) && password.equals(pass[i]))
            {
                valid=true;
                exists=1;
                break;
            }
            if(username.equals(users[i]))
            {
                exists=1;
            }
        }
        if(exists==0)
        {
            JOptionPane.showMessageDialog(LoginBox.this, "User not found! Please check if username is entered correctly. If you are new, click OK and try again!");
            JFrame load = new JFrame();
            load.setUndecorated(true); // Removes the title bar
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
            JLabel textLabel = new JLabel("Accounts Data resyncing with database, please wait..");
            textLabel.setFont(new Font("Monospaced", Font.BOLD,12));
            textLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(textLabel, BorderLayout.SOUTH);
            load.add(panel);
            load.setVisible(true);

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        //TIME CONSUMING OPERATION
                        refnote();
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
        if (valid) {
            //JOptionPane.showMessageDialog(LoginBox.this, "Login successful!");
            setVisible(false);
            newdashapp frame1 = new newdashapp(username);
            frame1.setVisible(true);
        } else {
            if(exists==1)
                JOptionPane.showMessageDialog(LoginBox.this, "Invalid password. Please try again.");
        }
    }

    public static void note()
    {
        users[0]="Pranav Gowrish";
        pass[0]="pgadmin";
        users[1]="Shreyas Naik";
        pass[1]="DASH@123";
        users[2]="Shirin";
        pass[2]="shirin08";
        users[3]="Kriti Gupta";
        pass[3]="kriti18";
        int i=4;
        try {
            String programDirectoryName = ".DASHAPP";
            String fileName = "users.txt";

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

            //Scanner sc = new Scanner(new File("users.txt"));
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String us=str.substring(0,str.indexOf("$"));
                String pa=str.substring(str.indexOf("$")+1);
                System.out.println("us="+us);
                System.out.println("pa="+pa);
                users[i]=us;
                pass[i]=pa;
                i++;
                //tlist[nr] = str;
                //nr++;
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public static void refnote() throws IOException
    {
        String programDirectoryName = ".DASHAPP";
        String fileName = "users.txt";

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
            fw = new FileWriter(file, true); // true for append mode
        } catch (IOException e) {
            System.err.println("Error creating FileWriter: " + e.getMessage());
        }

        BufferedWriter br = new BufferedWriter(fw);
        PrintWriter pr = new PrintWriter(br);


        //PrintWriter pr=new PrintWriter(br);

        String usrev[]=new String[100];
        String parev[]=new String[100];
        int len=0;

        try {
            String zapierUrl = "https://eog28ldw221ohny.m.pipedream.net"; // webhook URL

            URL url = new URL(zapierUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonData = "{ \"name\":\"" + "LOGINBOX" + "\" }";
            //String jsonData = "{ \"name\":\"" + "Pranav Gowrish" + "\" }";
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
                        if(key.equals("US"))
                        {
                            String Data[] = values.toArray(new String[0]);
                            //String Datarev[]=new String[Data.length];
                            //int ij=0;
                            for(int i=0;i<Data.length;i++)
                            {
                                usrev[i]=Data[i];
                                //ij++;
                            }
                            len=Data.length;

                        }
                        if(key.equals("PA"))
                        {
                            String Data[] = values.toArray(new String[0]);

                            //int ij=0;
                            for(int i=0;i<Data.length;i++)
                            {
                                parev[i]=Data[i];
                                //ij++;
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

        for(int i=0;i<len;i++)
        {
            pr.println(usrev[i]+"$"+parev[i]);
        }

        pr.close();
        br.close();
        fw.close();

    }

    public static void main(String[] args) {
        // Obtain the user's home directory
        String userHome = System.getProperty("user.home");

        // Define the directory name
        String programDirectory = userHome + File.separator + ".DASHAPP";

        // For Windows, hide the directory by setting the "hidden" attribute
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            setHiddenAttribute(programDirectory);
        }

        // Create the directory if it doesn't exist
        File directory = new File(programDirectory);
        if (!directory.exists()) {
            boolean created = directory.mkdirs(); // Creates the directory and any necessary parent directories
            if (created) {
                System.out.println("Directory created: " + programDirectory);
            } else {
                System.out.println("Failed to create directory: " + programDirectory);
            }
        } else {
            System.out.println("Directory already exists: " + programDirectory);
        }


        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new LoginBox().setVisible(true);
                }
            });
    }

    private static void setHiddenAttribute(String directory) {
        try {
            Process process = Runtime.getRuntime().exec("attrib +H " + directory);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
