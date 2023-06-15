import javax.swing.*;
import java.awt.event.*;

public class LambdaDemo {

public static void main(String[] args) { 

// THE BEFORE PICTURE
JButton b = new JButton("Help");
b.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {
        otherWindow.setVisible(true);
    }
});
ActionListener saver = new ActionListener() {
     public void actionPerformed(ActionEvent e) {
       dataModel.save();
    }
};

// THE AFTER PICTURE
JButton help = new JButton("Help");
help.addActionListener( e -> otherWindow.setVisible(true) );

saver =  e ->  dataModel.save();

// Put the handlers to use:
saveButton.addActionListener(saver);
saveMenuItem.addActionListener(saver);

}

// Stuff below here is dummy objects just to let the above compile

static JWindow otherWindow;
class DataModel {
	void save() {
		System.out.println("Hypothetically doing a save now");
	}
}
static DataModel dataModel;
static JButton saveButton;
static JMenuItem saveMenuItem;

}

