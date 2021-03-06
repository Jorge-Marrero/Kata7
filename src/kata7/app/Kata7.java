package kata7.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import kata7.control.*;

import kata7.model.Block;

public class Kata7 extends JFrame{
    private Map<String, Command> commands;
    private Block block;
    private BlockPanel blockDisplay;
    private BlockPresenter blockPresenter;
    
    public static void main(String[] args) {
        new Kata7().execute();
    }
    

    public Kata7(){
        this.setTitle("Block shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 722);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        this.commands = createCommands();
    }
    
    private void execute() {
        this.block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands = createCommands();
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel panel = new BlockPanel(Block.MAX);
        this.blockDisplay = panel;
        return panel;
    }

    private Component toolbar() {
        JMenuBar result = new JMenuBar();
        result.setLayout(new FlowLayout(FlowLayout.CENTER));
        result.add(button("D"));
        result.add(button("U"));
        result.add(button("L"));
        result.add(button("R"));
        return result;
    }

    private Map<String, Command> createCommands() {
        Map<String, Command> commands = new HashMap<>();
        commands.put("D", new DownCommand(block));
        commands.put("U", new UpCommand(block));
        commands.put("L", new LeftCommand(block));
        commands.put("R", new RightCommand(block));
        return commands;
    }

    private JButton button(String command) {
        JButton button = new JButton(command);
        button.addActionListener(e -> commands.get(command).execute());
        return button;
    }
}
