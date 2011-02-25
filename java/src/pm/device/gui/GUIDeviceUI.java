package pm.device.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import pm.event.Task;
import pm.event.EventManager;
import pm.value.Action;
import pm.value.Target;

import layout.TableLayout;
import layout.TableLayoutConstraints;

public class GUIDeviceUI extends JFrame {

    protected static final long serialVersionUID = 1L;
    
    protected JButton play;
    protected JButton pause;
    protected JButton resume;
    protected JButton next;
    protected JButton previous;
    protected JButton forward;
    protected JButton rewind;
    protected JButton mute;
    protected JButton volumeUp;
    protected JButton volumeDown;

    //TaskGatherer.add
    public GUIDeviceUI() {
        initComponents();
        setSize(30, 300);
        setVisible(true);
    }

    public void initComponents() {
        play = new JButton();
        pause = new JButton();
        resume = new JButton();
        next = new JButton();
        previous = new JButton();
        forward = new JButton();
        rewind = new JButton();
        mute = new JButton();
        volumeUp = new JButton();
        volumeDown = new JButton();


        setLayout(
                new TableLayout(new double[][] {
                        {TableLayout.PREFERRED},
                        {TableLayout.PREFERRED,
                            TableLayout.PREFERRED,
                            TableLayout.PREFERRED, 
                            TableLayout.PREFERRED,
                            TableLayout.PREFERRED,
                            TableLayout.PREFERRED, 
                            TableLayout.PREFERRED,
                            TableLayout.PREFERRED,
                            TableLayout.PREFERRED,
                            TableLayout.PREFERRED,
                            TableLayout.PREFERRED}
                })
        );

        //---- play ----
        play.setText("play");
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playAction(e);
            }
        });
        add(play, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- pause ----
        pause.setText("pause");
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pauseAction(e);
            }
        });
        add(pause, new TableLayoutConstraints(0, 1, 0, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- resume ----
        resume.setText("resume");
        resume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resumeAction(e);
            }
        });
        add(resume, new TableLayoutConstraints(0, 2, 0, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- next ----
        next.setText("next");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextAction(e);
            }
        });
        add(next, new TableLayoutConstraints(0, 3, 0, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- previous ----
        previous.setText("previous");
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previousAction(e);
            }
        });
        add(previous, new TableLayoutConstraints(0, 4, 0, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- forward ----
        forward.setText("forward");
        forward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                forwardAction(e);
            }
        });
        add(forward, new TableLayoutConstraints(0, 5, 0, 5, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- rewind ----
        rewind.setText("rewind");
        rewind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rewindAction(e);
            }
        });
        add(rewind, new TableLayoutConstraints(0, 6, 0, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- mute ----
        mute.setText("mute");
        mute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                muteAction(e);
            }
        });
        add(mute, new TableLayoutConstraints(0, 7, 0, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- volumeUp ----
        volumeUp.setText("volume up");
        volumeUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volumeUpAction(e);
            }
        });
        add(volumeUp, new TableLayoutConstraints(0, 8, 0, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- volumeDown ----
        volumeDown.setText("volume down");
        volumeDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volumeDownAction(e);
            }
        });
        add(volumeDown, new TableLayoutConstraints(0, 9, 0, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
    }
    
    protected void playAction(ActionEvent e) {
        EventManager.add(new Task(Action.PLAY, Target.APPLICATION));
    }

    protected void pauseAction(ActionEvent e) {
        EventManager.add(new Task(Action.PAUSE, Target.APPLICATION));
    }

    protected void resumeAction(ActionEvent e) {
        EventManager.add(new Task(Action.RESUME, Target.APPLICATION));
    }

    protected void nextAction(ActionEvent e) {
        EventManager.add(new Task(Action.NEXT, Target.APPLICATION));
    }

    protected void previousAction(ActionEvent e) {
        EventManager.add(new Task(Action.PREVIOUS, Target.APPLICATION));
    }

    protected void forwardAction(ActionEvent e) {
        EventManager.add(new Task(Action.FORWARD, Target.APPLICATION));
    }

    protected void rewindAction(ActionEvent e) {
        EventManager.add(new Task(Action.REWIND, Target.APPLICATION));
    }

    protected void muteAction(ActionEvent e) {
        EventManager.add(new Task(Action.MUTE, Target.APPLICATION));
    }

    protected void volumeUpAction(ActionEvent e) {
        EventManager.add(new Task(Action.VOLUME_UP, Target.APPLICATION));
    }

    protected void volumeDownAction(ActionEvent e) {
        EventManager.add(new Task(Action.VOLUME_DOWN, Target.APPLICATION));
    }
}
