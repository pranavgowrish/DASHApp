import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.*;
import java.net.*;
import java.nio.file.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.BorderLayout; 
import java.nio.file.*;

public class GifPanel extends JPanel {
    static JPanel buttonPanel;
    static String name;
    static String ncode;
    static String description;
    public GifPanel() {
        try {
            // Windows Look and Feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());

        JPanel gifPanel = new JPanel();
        gifPanel.setBackground(Color.WHITE);
        JLabel gifLabel = new JLabel(new ImageIcon(LoginBox.class.getResource("/apphome.png")));

        gifPanel.add(gifLabel);
        add(gifPanel, BorderLayout.CENTER);

        JLabel usv = new JLabel("Version 3.0");
        usv.setBounds(510,25,900,600);
        usv.setFont(new Font("Monospaced", Font.BOLD,17));
        usv.setForeground(new Color(255, 243, 243));
        gifLabel.add(usv);

        System.out.println("CODE by shreyas="+encode());

        // Button Panel
        buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5)); // 3 buttons vertically
        JButton button1 = new JButton("View all Member Data");
        button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://docs.google.com/spreadsheets/d/e/2PACX-1vQkBq9LdTr9PjG7BzsNcogW81rxw9TWfoxxvSTR_5h32fU2QDTfMc-DKidr_Jb6hi-7J2dgleP57WY2/pubhtml?gid=0&single=true"));
                    } catch (IOException | URISyntaxException ef) {
                        ef.printStackTrace();
                    }
                }
            });

        JButton button2 = new JButton("View Tasks Record");
        button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://docs.google.com/spreadsheets/d/e/2PACX-1vQkBq9LdTr9PjG7BzsNcogW81rxw9TWfoxxvSTR_5h32fU2QDTfMc-DKidr_Jb6hi-7J2dgleP57WY2/pubhtml?gid=117749694&single=true"));
                    } catch (IOException | URISyntaxException ef) {
                        ef.printStackTrace();
                    }
                }
            });

        JButton button3 = new JButton("Create Certificate");
        button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame certf=new JFrame();
                    certf.setTitle("DASH Certificate Generator");
                    certf.setSize(400, 300);
                    certf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    certf.setLocationRelativeTo(null);
                    ImageIcon im = new ImageIcon(LoginBox.class.getResource("/logo.png"));
                    certf.setIconImage(im.getImage());
                    JTextField nameField;
                    JTextArea descriptionArea;

                    JPanel panel = new JPanel(new BorderLayout());

                    // Name input
                    JPanel namePanel = new JPanel();
                    JLabel nameLabel = new JLabel("Name:");
                    nameField = new JTextField(20);
                    namePanel.add(nameLabel);
                    namePanel.add(nameField);

                    // Description input
                    JPanel descriptionPanel = new JPanel();
                    JLabel descriptionLabel = new JLabel("Remarks/Comments:");
                    descriptionArea = new JTextArea(8, 40);
                    descriptionArea.setLineWrap(true);
                    JScrollPane scrollPane = new JScrollPane(descriptionArea);
                    descriptionPanel.add(descriptionLabel);
                    descriptionPanel.add(scrollPane);

                    // Button panel
                    JPanel buttonPanel = new JPanel();
                    JButton submitButton = new JButton("Generate");
                    JButton cancelButton = new JButton("Cancel");
                    buttonPanel.add(submitButton);
                    buttonPanel.add(cancelButton);

                    // Add components to main panel
                    panel.add(namePanel, BorderLayout.NORTH);
                    panel.add(descriptionPanel, BorderLayout.CENTER);
                    panel.add(buttonPanel, BorderLayout.SOUTH);

                    // Add main panel to the frame
                    certf.add(panel);
                    certf.setVisible(true);

                    // Action listeners for buttons
                    submitButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                name = nameField.getText();
                                name=name.trim();
                                description = descriptionArea.getText();
                                description=description.trim();
                            
                                System.out.println("Name:" + name);
                                System.out.println("Description:" + description);

                                JFrame load = new JFrame();
                                load.setUndecorated(true); // Removes the title bar
                                load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                load.setSize(400, 300);
                                load.setLocationRelativeTo(null); // Centers the frame on screen
                                load.setAlwaysOnTop(true);
                                /*load.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowActivated(WindowEvent e) {
                                load.setAlwaysOnTop(false); // Allow otherFrame to come in front
                                load.setAlwaysOnTop(true); // Keep otherFrame on top
                                }

                                @Override
                                public void windowDeactivated(WindowEvent e) {
                                load.setAlwaysOnTop(false); // Release always on top when not in focus
                                }
                                });*/
                                JPanel panel = new JPanel(new BorderLayout());
                                // Add loading GIF
                                ImageIcon loadingIcon = new ImageIcon(LoginBox.class.getResource("/loadsync.gif"));
                                ImageIcon finishload = new ImageIcon( LoginBox.class.getResource("/donesync.gif"));
                                JLabel loadingLabel = new JLabel(loadingIcon);
                                loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                panel.add(loadingLabel, BorderLayout.CENTER);
                                // Add text
                                JLabel textLabel = new JLabel("Generating Certificate for "+name+", please wait..");
                                textLabel.setFont(new Font("Monospaced", Font.BOLD,12));
                                textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                panel.add(textLabel, BorderLayout.SOUTH);
                                load.add(panel);
                                load.setVisible(true);

                                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                                        @Override
                                        protected Void doInBackground() throws Exception {
                                            //TIME CONSUMING OPERATION
                                            String writer=newdashapp.name;
                                            String rol;
                                            if(newdashapp.roles.contains("CoFounder"))
                                            {
                                                rol="CoFounder of The DASH Project";
                                            }
                                            else
                                            {
                                                rol="Founder of The DASH Project";
                                            }
                                            String verify=encode();
                                            //SHREYAS CODE HERE

                                            //SHREYAS CODE END

                                            try {
                                                String zapierUrl = "https://eo3v4imi4oy6p1h.m.pipedream.net"; // Replace with your Zapier webhook URL

                                                URL url = new URL(zapierUrl);
                                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                                connection.setRequestMethod("POST");
                                                connection.setRequestProperty("Content-Type", "application/json");
                                                connection.setDoOutput(true);

                                                //String jsonData = "{ \"name\":\"" + newdashapp.name + "\" }";
                                                //String jsonData = "{ \"user\": \"" + writer + "\", \"rol\": \"" + rol + "\", \"name\": \"" + name + "\", \"text\": \"" + description + "\", \"code\": \"" + verify + "\" }";
                                                //String jsonData = "{ \"user\":\"" + writer + "\", \"rol\":\"" + rol + "\", \"name\":\"" + name + "\", \"text\":\"" + description + "\", \"code\":\"" + verify + "\" }";

                                                //IMP//String jsonData = "{ \"user\": \"" + writer + "\", \"rol\": \"" + rol + "\", \"name\": \"" + name+ "\", \"text\": \"" + description + "\", \"code\": \"" + verify + "\", \"hr\": \"" + writer + "\" }";

                                                //String jsonData = "{ \"name\":\""+cnam+"\", "+ "\"signer\": \"" + signer+"\", "+ "\"desn\": \"" + desn+"\" }";

                                                //WORKS//String jsonData = "{ \"name\": \"" + newdashapp.name + "\", \"col\": \"" + rol + "\", \"desc\": \"" + description + "\", \"hr\": \"" + writer + "\" }";
                                                String jsonData = "{ \"user\": \"" + writer + "\", \"rol\": \"" + rol + "\", \"name\": \"" + name+ "\", \"desc\": \"" + description + "\", \"code\": \"" + verify + "\" }";

                                                //String jsonData = "{ \"name\": \"" + newdashapp.name + "\", \"col\": \"" + rol + "\", \"desc\": \"" + description + "\", \"hr\": \"" + writer + "\", \"value5\": \"" + verify + "\" }";

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
                                            //OPERATION END
                                            return null;
                                        }

                                        @Override
                                        protected void done() {

                                            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                                                    @Override
                                                    protected Void doInBackground() throws Exception {
                                                        //TIME CONSUMING OPERATION
                                                        loadingLabel.setIcon(finishload);
                                                        javax.swing.Timer timer = new javax.swing.Timer(1500, e -> {
                                                                        load.setVisible(false);
                                                                });
                                                        timer.setRepeats(false);
                                                        timer.start();
                                                        //OPERATION END
                                                        return null;
                                                    }

                                                    @Override
                                                    protected void done() {
                                                        try {
                                                            Desktop.getDesktop().browse(new URI("https://docs.google.com/spreadsheets/d/1zuOQFY9NANujW6HW5Oy9ihuN7rbV-Vy_9ze1EgVxnp0/export?format=pdf&portrait=true&gridlines=false&size=a4"));
                                                        } catch (IOException | URISyntaxException ef) {
                                                            ef.printStackTrace();
                                                        }
                                                    }
                                                };
                                            worker.execute();

                                        }
                                    };
                                worker.execute();

                                //syncgifdone 

                                // Close the frame
                                certf.dispose();
                            }
                        });

                    cancelButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Close the frame
                                certf.dispose();
                            }
                        });

                }
            });

        JButton button4 = new JButton("Verify Certificate");
        button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame certf=new JFrame();
                    certf.setTitle("DASH Certificate Verifier");
                    certf.setSize(400, 100);
                    certf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    certf.setLocationRelativeTo(null);
                    ImageIcon im = new ImageIcon(LoginBox.class.getResource("/logo.png"));
                    certf.setIconImage(im.getImage());
                    JTextField nameField;
                    JTextArea descriptionArea;

                    JPanel panel = new JPanel(new BorderLayout());

                    // Name input
                    JPanel namePanel = new JPanel();
                    JLabel nameLabel = new JLabel("Enter Code:");
                    nameField = new JTextField(20);
                    namePanel.add(nameLabel);
                    namePanel.add(nameField);

                    JLabel result=new JLabel("");

                    // Button panel
                    JPanel buttonPanel = new JPanel();
                    JButton submitButton = new JButton("Verify");
                    JButton cancelButton = new JButton("Cancel");
                    buttonPanel.add(submitButton);
                    buttonPanel.add(cancelButton);

                    namePanel.add(result);

                    // Add components to main panel
                    panel.add(namePanel, BorderLayout.NORTH);
                    panel.add(buttonPanel, BorderLayout.SOUTH);

                    // Add main panel to the frame
                    certf.add(panel);
                    certf.setVisible(true);

                    // Action listeners for buttons
                    submitButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                ncode = nameField.getText();
                                ncode=ncode.trim();
                            
                                System.out.println("Name:" + name);

                                /*JFrame load = new JFrame();
                                load.setUndecorated(true); // Removes the title bar
                                load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                load.setSize(400, 300);
                                load.setLocationRelativeTo(null); // Centers the frame on screen
                                load.setAlwaysOnTop(true);

                                JPanel panel = new JPanel(new BorderLayout());
                                // Add loading GIF
                                ImageIcon loadingIcon = new ImageIcon("loadsync.gif");
                                ImageIcon finishload = new ImageIcon("donesync.gif");
                                JLabel loadingLabel = new JLabel(loadingIcon);
                                loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                panel.add(loadingLabel, BorderLayout.CENTER);
                                // Add text
                                JLabel textLabel = new JLabel("Generating Certificate for "+name+", please wait..");
                                textLabel.setFont(new Font("Monospaced", Font.BOLD,12));
                                textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                panel.add(textLabel, BorderLayout.SOUTH);
                                load.add(panel);
                                load.setVisible(true);*/

                                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                                        @Override
                                        protected Void doInBackground() throws Exception {
                                            //TIME CONSUMING OPERATION
                                            String verification=decode(ncode);
                                            //SHREYAS CODE HERE
                                            result.setText(verification);
                                            nameField.setVisible(false);
                                            nameLabel.setVisible(false);
                                            submitButton.setVisible(false);
                                            cancelButton.setVisible(false);
                                            //SHREYAS CODE END

                                            //OPERATION END
                                            return null;
                                        }

                                        @Override
                                        protected void done() {

                                            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                                                    @Override
                                                    protected Void doInBackground() throws Exception {
                                                        //TIME CONSUMING OPERATION
                                                        /*loadingLabel.setIcon(finishload);
                                                        javax.swing.Timer timer = new javax.swing.Timer(1500, e -> {
                                                        load.setVisible(false);
                                                        });
                                                        timer.setRepeats(false);
                                                        timer.start();*/
                                                        //OPERATION END
                                                        return null;
                                                    }

                                                    @Override
                                                    protected void done() {
                                                        /*try {
                                                        Desktop.getDesktop().browse(new URI("https://docs.google.com/spreadsheets/d/1zuOQFY9NANujW6HW5Oy9ihuN7rbV-Vy_9ze1EgVxnp0/export?format=pdf&portrait=true&gridlines=false&size=a4"));
                                                        } catch (IOException | URISyntaxException ef) {
                                                        ef.printStackTrace();
                                                        }*/
                                                    }
                                                };
                                            worker.execute();

                                        }
                                    };
                                worker.execute();

                                //syncgifdone 

                                // Close the frame
                            }
                        });

                    cancelButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Close the frame
                                certf.dispose();
                            }
                        });

                }
            });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        add(buttonPanel, BorderLayout.LINE_END);
        buttonPanel.setVisible(false);
        if(newdashapp.adminv==1)
            buttonPanel.setVisible(true);
    }

    String encode()
    {
        int i=0,rand;
        String letters[]= new String[62],serial="",binary_serial="",temp="",code="";
        char ch;
        double r;
        for(i=0;i<6;i++)
        { 
            r=Math.random();
            rand=(int)(r*3); 
            serial+=rand==0?String.valueOf((int)(r * (10))):rand==1?(char)((int)(r * (26)) + 97):(char)((int)(r * (26)) + 65);
        }
        code=" ---------------------- ";
        for (i=0;i<serial.length();i+=2)
        {
            code+="|||";
            try 
            {
                temp=Integer.toBinaryString(Integer.parseInt(String.valueOf(serial.charAt(i))));
            }
            catch(Exception e)
            {
                temp=Integer.toBinaryString(serial.charAt(i));
            }
            while(temp.length()<8)
                temp='0'+temp;
            binary_serial+=temp;
            for(int j=0;j<temp.length();j++)
                code+=temp.charAt(j)=='0'?"|":"/";

            try 
            {
                temp=Integer.toBinaryString(Integer.parseInt(String.valueOf(serial.charAt(i+1))));
            }
            catch(Exception e)
            {
                temp=Integer.toBinaryString(serial.charAt(i+1));
            }
            while(temp.length()<8)
                temp='0'+temp;
            binary_serial+=temp;
            for(int j=0;j<temp.length();j++)
                code+=temp.charAt(j)=='0'?"|":"/";
            code+="||| ";
        }
        code+="----------------------";
        System.out.print(code);
        return code;
    }

    String decode(String code)
    {
        String serial="",binary_serial="",temp="";
        int i=0, j=0, d1=0,d2=0;
        code=code.trim()+' ';
        for (i=0;i<code.length();i++)
        {
            if (code.charAt(i)==' ')
            {   
                if (temp.charAt(0)=='-')
                {
                    temp="";
                    continue; 
                }
                for(j=0;j<temp.length();j++)
                    binary_serial+=temp.charAt(j)=='|'?"0":temp.charAt(j)=='/'?"1":"";
                d1=Integer.parseInt(binary_serial.substring(3,11),2);
                d2=Integer.parseInt(binary_serial.substring(11,19),2);
                serial+=(d1<=9?String.valueOf(d1):(char)d1);
                serial+=(d2<=9?String.valueOf(d2):(char)d2);
                binary_serial="";
                temp="";
            }
            else
                temp+=code.charAt(i);
        }
        System.out.print(serial);
        return serial;
    }
}