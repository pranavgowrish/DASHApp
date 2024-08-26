import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class TaskPanel extends JPanel {
    public TaskPanel() {
        setLayout(new BorderLayout());
        add(new DynamicButtonAppd());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Main Panel");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(new TaskPanel());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
            });
    }
}

class DynamicButtonAppd extends JPanel {
    String ar[] = new String[20];//{"So basically this is task Hi1", "So basically this is task Hello1", "So basically this is task Hi2", "So basically this is task Hello2", "So basically this is task Hi3", "So basically this is task Hello3", "So basically this is task Hi4", "So basically this is task Hello4", "So basically this is task Hi5", "So basically this is task Hello5"};
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel panel = new JPanel();
    double hours;

    double exh;
    public DynamicButtonAppd() {
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());

        UIManager.put("Button.background", new ColorUIResource(new Color(57, 108, 125)));
        UIManager.put("Button.foreground", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.font", new Font("Monospaced", Font.BOLD, 14));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set layout to BoxLayout with Y_AXIS alignment
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setBackground(new Color(57, 108, 125));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        Font font = new Font("Georgia", Font.BOLD, 20);
        JLabel label = new JLabel("Your Tasks", SwingConstants.CENTER);
        label.setFont(font);
        JButton createButton = new JButton("🔃 Refresh 🔃");

        JButton addButton=new JButton("Add Extra Task");

        addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //checktasks();
                    try {
                        // Windows Look and Feel
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    } catch (Exception ed) {
                        ed.printStackTrace();
                    }
                    JFrame fr=new JFrame("Choose Extra Work");
                    fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fr.setLayout(new BorderLayout());
                    fr.setSize(400, 200);
                    fr.setLocationRelativeTo(null);

                    String[] data = {"Update Website", "Work on Quiz and Quotes", "Work on Photography", "Make Video", "Create Poem","Recite Poem","Create Artwork","Create Post for Social Media","Work on Social Media Marketing","Create Meme for Social Media","Write Blog for Topic","Upload Blog to Website","Complete Research for Topic","Something Else"};
                    JList<String> list = new JList<>(data);
                    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    JScrollPane scrollPane = new JScrollPane(list);
                    fr.add(scrollPane);
                    fr.setVisible(true);

                    list.addListSelectionListener(new ListSelectionListener() {
                            @Override
                            public void valueChanged(ListSelectionEvent e) {
                                if (!e.getValueIsAdjusting()) {
                                    String selectedValue = list.getSelectedValue();
                                    if(selectedValue.equals("Something Else"))
                                    {
                                        fr.setVisible(false);
                                        //JDIALOG BOX WITH 2 INPUTS
                                        JPanel panel1 = new JPanel();
                                        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
                                        JTextField textField1 = new JTextField();
                                        JTextField textField2 = new JTextField();
                                        panel1.add(new JLabel("What is the Task?"));
                                        panel1.add(textField1);
                                        panel1.add(new JLabel("How many hours does it take?[enter as decimal]"));
                                        panel1.add(textField2);

                                        // Show the option pane with custom panel
                                        int result = JOptionPane.showConfirmDialog(null, panel1, "Two Input Dialog", JOptionPane.OK_CANCEL_OPTION);
                                        if (result == JOptionPane.OK_OPTION) {
                                            // Retrieve input values
                                            String input1 = textField1.getText();
                                            String input2 = textField2.getText();
                                            exh=Double.valueOf(input2);
                                            ar[0]=input1;
                                            createButtons(panel, 1);
                                            System.out.println("ar="+ar[0]);
                                            System.out.println("Input1="+input1);
                                        } else {
                                            System.out.println("User cancelled the operation.");
                                        }

                                    }
                                    else
                                    {
                                        ar[0]=selectedValue;
                                        createButtons(panel, 1);
                                        fr.setVisible(false);
                                    }
                                    //JOptionPane.showMessageDialog(JListExample.this, "Selected: " + selectedValue);
                                }
                            }
                        });
                }
            });

        createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checktasks();
                    addButton.setVisible(true);
                }
            });

        mainPanel.add(label, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Add createButton to the button panel
        buttonPanel.add(createButton);

        // Add addButton to the button panel
        buttonPanel.add(addButton);
        addButton.setVisible(false);

        // Add the button panel to the main panel's south position
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        //mainPanel.add(createButton, BorderLayout.SOUTH);
        //mainPanel.add(addButton,BorderLayout.SOUTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Add panel to display buttons
        add(mainPanel);
    }

    private void checktasks()
    {
        ImageIcon loadingIcon = new ImageIcon( LoginBox.class.getResource("/loadsync.gif"));

        ImageIcon finishload = new ImageIcon( LoginBox.class.getResource("/donesync.gif"));
        JLabel loadingLabel = new JLabel(loadingIcon);
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(loadingLabel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    //TIME CONSUMING OPERATION
                    int arn=0;
                    try {
                        String zapierUrl = "https://eo4wru2b6jxuu7h.m.pipedream.net"; // Replace with your Zapier webhook URL

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
                            for (Map.Entry<String, java.util.List<String>> entry : headerFields.entrySet()) {
                                String key = entry.getKey();
                                java.util.List<String> values = entry.getValue();
                                System.out.println(key + ": " + values);
                                if(key!=null)
                                {
                                    if(key.equals("Hr"))
                                    {
                                        String Hours[]=values.toArray(new String[0]);
                                        newdashapp.us4.setText("Total Hours Spent: "+Hours[0]);
                                        newdashapp.pictureLabel.repaint();
                                        hours=Double.valueOf(Hours[0]);
                                    }
                                    if(key.equals("Top"))
                                    {
                                        String Topic[]=values.toArray(new String[0]);
                                        newdashapp.us5.setText("Current Topic: "+Topic[0]);
                                        newdashapp.pictureLabel.repaint();
                                        newdashapp.topic=Topic[0];
                                    }
                                    if(key.equals("Tasks"))
                                    {
                                        String tasksrev[] = values.toArray(new String[0]);
                                        String Tasks[]=new String[tasksrev.length];
                                        int ij=0;
                                        for(int i=tasksrev.length-1;i>=0;i--)
                                        {
                                            Tasks[ij]=tasksrev[i];
                                            ij++;
                                        }
                                        for (int i=0;i<Tasks.length;i++)
                                        {
                                            System.out.println(Tasks[i]);
                                            switch(i){
                                                case 0:
                                                    if(Tasks[i].equals("W"))
                                                    {
                                                        ar[arn]="Update Website";
                                                        arn++;
                                                    }
                                                    else if(Tasks[i].equals("Q"))
                                                    {
                                                        ar[arn]="Work on Quiz and Quotes";
                                                        arn++;
                                                    }
                                                    else if(Tasks[i].equals("P"))
                                                    {
                                                        ar[arn]="Work on Photography";
                                                        arn++;
                                                    }
                                                    break;
                                                case 1:
                                                    if(Tasks[i].equals("TRUE"))
                                                    {
                                                        ar[arn]="Make Video";
                                                        arn++;
                                                    }
                                                    break;
                                                case 2:
                                                    if(Tasks[i].equals("C"))
                                                    {
                                                        ar[arn]="Create Poem";
                                                        arn++;
                                                    }
                                                    else if(Tasks[i].equals("R"))
                                                    {
                                                        ar[arn]="Recite Poem";
                                                        arn++;
                                                    }
                                                    break;
                                                case 3:
                                                    if(Tasks[i].equals("TRUE"))
                                                    {
                                                        ar[arn]="Create Artwork";
                                                        arn++;
                                                    }
                                                    break;
                                                case 4:
                                                    if(Tasks[i].equals("P"))
                                                    {
                                                        ar[arn]="Create Post for Social Media";
                                                        arn++;
                                                    }
                                                    else if(Tasks[i].equals("M"))
                                                    {
                                                        ar[arn]="Work on Social Media Marketing";
                                                        arn++;
                                                    }
                                                    else if(Tasks[i].equals("L"))
                                                    {
                                                        ar[arn]="Create Meme for Social Media";
                                                        arn++;
                                                    }
                                                    break;
                                                case 5:
                                                    if(Tasks[i].equals("W"))
                                                    {
                                                        ar[arn]="Write Blog for Topic";
                                                        arn++;
                                                    }
                                                    else if(Tasks[i].equals("U"))
                                                    {
                                                        ar[arn]="Upload Blog to Website";
                                                        arn++;
                                                    }
                                                    break;
                                                case 6:
                                                    if(Tasks[i].equals("TRUE"))
                                                    {
                                                        ar[arn]="Complete Research for Topic";
                                                        arn++;
                                                    }
                                                    break;

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
                    //CREATE TASKS
                    int numOfButtons = arn; //Integer.parseInt(textField.getText());//Important!!!
                    if (numOfButtons > 0) {
                        panel.removeAll();
                        createButtons(panel, numOfButtons);
                    } else {
                        panel.removeAll();
                        JLabel labelc = new JLabel(" ");
                        JLabel labelc1 = new JLabel("\uD83C\uDF89");// This is unicode for 🥳
                        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 50);
                        labelc1.setFont(emojiFont);
                        labelc1.setAlignmentX(Component.CENTER_ALIGNMENT);

                        panel.add(labelc, BorderLayout.CENTER);
                        panel.add(labelc1);
                        Font font2 = new Font("Monospaced", Font.BOLD, 30);
                        JLabel label2 = new JLabel("All Tasks Completed");
                        label2.setFont(font2);
                        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        panel.add(label2, BorderLayout.CENTER);
                        panel.revalidate();
                        panel.repaint();
                    }
                    //OPERATION END
                    return null;
                }

                @Override
                protected void done() {
                    ImageIcon finishload = new ImageIcon( LoginBox.class.getResource("/donesync.gif"));

                    JLabel loadingLabel = new JLabel(finishload);
                    loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    //loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    panel.add(loadingLabel, BorderLayout.NORTH);
                    panel.revalidate();
                    panel.repaint();
                    javax.swing.Timer timer = new javax.swing.Timer(1500, e -> {
                                    loadingLabel.setVisible(false);
                            });
                    timer.setRepeats(false);
                    timer.start();
                }
            };
        worker.execute();

    }

    private void createButtons(JPanel panel, int numOfButtons) {
        //panel.removeAll();
        //addLoadingGif(panel); // Display loading GIF
        System.out.println("Button creating");
        int delay = 0; // Delay between each button creation
        javax.swing.Timer timer = new javax.swing.Timer(delay * numOfButtons, new ActionListener() {
                    int count = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (count < numOfButtons) {
                            JButton button = new JButton(ar[count]);
                            button.setPreferredSize(new Dimension(1000, 30)); // Size
                            button.setMaximumSize(new Dimension(1000, 30)); // Set maximum size
                            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Align buttons to the center
                            button.addActionListener(new ButtonClickListener(panel, button));
                            panel.add(button);
                            count++;
                            System.out.println("if stat!");
                        } else {
                            //removeLoadingGif(panel); // Remove loading GIF after all buttons are created
                            ((javax.swing.Timer) e.getSource()).stop(); // Stop the timer
                            panel.revalidate();
                            panel.repaint();
                            System.out.println("else stat!");
                        }
                    }
                });
        timer.start();
        System.out.println("Button created!");

    }

    

    private class ButtonClickListener implements ActionListener {
        private JPanel panel;
        private JButton button;

        public ButtonClickListener(JPanel panel, JButton button) {
            this.panel = panel;
            this.button = button;
            try {
                // Windows Look and Feel
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            double oldh=hours;

            double sheethour;
            String sheethistory;
            int choice = JOptionPane.showConfirmDialog(DynamicButtonAppd.this,
                    "Are you sure you're done with \"" + button.getText() + "\"?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {

                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            //TIME CONSUMING OPERATION
                            panel.remove(button);
                            panel.revalidate();
                            panel.repaint();

                            String col="";
                            String desc="";
                            LocalDateTime now = LocalDateTime.now();

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            String formattedDateTime = now.format(formatter);
                            System.out.println("button="+button.getText());
                            switch(button.getText())
                            {
                                case "Update Website":
                                    col="H";
                                    desc="Website Updated";
                                    hours+=0.75;
                                    break;
                                case "Work on Quiz and Quotes":
                                    col="H";
                                    desc="Quiz&Quotes done";
                                    hours+=0.75;
                                    break;
                                case "Work on Photography":
                                    col="H";
                                    desc="Photo done";
                                    hours+=0.25;
                                    break;
                                case "Make Video":
                                    col="G";
                                    desc="Video created";
                                    hours+=1.5;
                                    break;
                                case "Create Poem":
                                    col="F";
                                    desc="Poem created";
                                    hours+=0.5;
                                    break;
                                case "Recite Poem":
                                    col="F";
                                    desc="Poem recited";
                                    hours+=0.17;
                                    break;
                                case "Create Artwork":
                                    col="E";
                                    desc="Artwork created";
                                    hours+=2;
                                    break;
                                case "Create Post for Social Media":
                                    col="D";
                                    desc="Post created";
                                    hours+=0.5;
                                    break;
                                case "Work on Social Media Marketing":
                                    col="D";
                                    desc="Marketing done";
                                    hours+=0.5;
                                    break;
                                case "Create Meme for Social Media":
                                    col="D";
                                    desc="Meme created";
                                    hours+=0.5;
                                    break;
                                case "Write Blog for Topic":
                                    col="C";
                                    desc="Blog written";
                                    hours+=1;
                                    break;
                                case "Upload Blog to Website":
                                    col="C";
                                    desc="Blog uploaded";
                                    hours+=0.25;
                                    break;
                                case "Complete Research for Topic":
                                    col="B";
                                    desc="Research done";
                                    hours+=2;
                                    break;
                                default:
                                    col="I";
                                    desc=button.getText()+" done ["+exh+" hours]";
                                    hours+=exh;
                                    break;
                            }
                            try
                            {
                                BarGraphPanel.add(hours-oldh);
                            }
                            catch (IOException ioe)
                            {
                                ioe.printStackTrace();
                            }
                            try
                            {
                                HistoryPanel.add(desc);
                            }
                            catch (IOException ioe)
                            {
                                ioe.printStackTrace();
                            }
                            try {
                                String zapierUrl = "https://eo7v6ry4pm8mvy5.m.pipedream.net"; // webhook URL

                                URL url = new URL(zapierUrl);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("POST");
                                connection.setRequestProperty("Content-Type", "application/json");
                                connection.setDoOutput(true);

                                //String jsonData = "{ \"name\":\"" + newdashapp.name + "\" }";
                                //String jsonData = "{ \"name\":\"" + "Pranav Gowrish" + "\" }";
                                String jsonData = "{ \"name\": \"" + newdashapp.name + "\", \"col\": \"" + col + "\", \"desc\": \"" + desc+" by "+newdashapp.name+" at "+formattedDateTime + "\", \"hr\": \"" + hours + "\" }";
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
                                    for (Map.Entry<String, java.util.List<String>> entry : headerFields.entrySet()) {
                                        String key = entry.getKey();
                                        java.util.List<String> values = entry.getValue();
                                        System.out.println(key + ": " + values);
                                        if(key!=null)
                                        {
                                            /*if(key.equals("Hrs"))
                                            {
                                            String Hr[] = values.toArray(new String[0]);
                                            sheethour=Double.parseDouble(Hr[0].toString());
                                            }
                                            if(key.equals("His"))
                                            {
                                            String His[] = values.toArray(new String[0]);
                                            sheethistory=His[0].toString();
                                            }*/
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
                            newdashapp.us4.setText("Total Hours Spent: "+hours);
                            newdashapp.pictureLabel.repaint();

                            System.out.println("sv="+BarGraphPanel.sv);
                            System.out.println("history="+HistoryPanel.history);

                            try {
                                String zapierUrl = "https://eotziwvnkl463mc.m.pipedream.net"; // Replace with your Zapier webhook URL

                                URL url = new URL(zapierUrl);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("POST");
                                connection.setRequestProperty("Content-Type", "application/json");
                                connection.setDoOutput(true);

                                String jsonData = "{ \"name\": \"" + newdashapp.name + "\", \"grph\": \"" + BarGraphPanel.sv + "\", \"hist\": \"" + HistoryPanel.history + "\" }";
                                //String jsonData = "{ \"name\":\"" + newdashapp.name + "\" }";
                                //String jsonData = "{ \"name\":\"" + "Pranav Gowrish" + "\" }";
                                //String jsonData = "{ \"name\": \"" + newdashapp.name + "\", \"col\": \"" + col + "\", \"desc\": \"" + desc+" by "+newdashapp.name+" at "+formattedDateTime + "\", \"hr\": \"" + hours + "\" }";
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
                                    for (Map.Entry<String, java.util.List<String>> entry : headerFields.entrySet()) {
                                        String key = entry.getKey();
                                        java.util.List<String> values = entry.getValue();
                                        System.out.println(key + ": " + values);
                                        if(key!=null)
                                        {
                                            /*if(key.equals("Hrs"))
                                            {
                                            String Hr[] = values.toArray(new String[0]);
                                            sheethour=Double.parseDouble(Hr[0].toString());
                                            }
                                            if(key.equals("His"))
                                            {
                                            String His[] = values.toArray(new String[0]);
                                            sheethistory=His[0].toString();
                                            }*/
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
                            newdashapp.notif("Task Successfully Marked Completed");
                        }
                    };
                worker.execute();                
            }
        }
    }
}
