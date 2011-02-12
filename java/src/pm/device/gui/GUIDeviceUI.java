package pm.device.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import pm.Action;
import pm.Target;
import pm.action.ActionProvider;

import layout.TableLayout;
import layout.TableLayoutConstraints;

public class GUIDeviceUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JButton play;
    private JButton pause;
    private JButton resume;
    private JButton next;
    private JButton previous;
    private JButton forward;
    private JButton rewind;
    private JButton mute;
    private JButton volumeUp;
    private JButton volumeDown;

    //ActionProvider.add
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
    
    private void playAction(ActionEvent e) {
        ActionProvider.add(Action.PLAY.setTarget(Target.APPLICATION));
    }

    private void pauseAction(ActionEvent e) {
        ActionProvider.add(Action.PAUSE.setTarget(Target.APPLICATION));
    }

    private void resumeAction(ActionEvent e) {
        ActionProvider.add(Action.RESUME.setTarget(Target.APPLICATION));
    }

    private void nextAction(ActionEvent e) {
        ActionProvider.add(Action.NEXT.setTarget(Target.APPLICATION));
    }

    private void previousAction(ActionEvent e) {
        ActionProvider.add(Action.PREVIOUS.setTarget(Target.APPLICATION));
    }

    private void forwardAction(ActionEvent e) {
        ActionProvider.add(Action.FORWARD.setTarget(Target.APPLICATION));
    }

    private void rewindAction(ActionEvent e) {
        ActionProvider.add(Action.REWIND.setTarget(Target.APPLICATION));
    }

    private void muteAction(ActionEvent e) {
        ActionProvider.add(Action.MUTE.setTarget(Target.APPLICATION));
    }

    private void volumeUpAction(ActionEvent e) {
        ActionProvider.add(Action.VOLUME_UP.setTarget(Target.APPLICATION));
    }

    private void volumeDownAction(ActionEvent e) {
        ActionProvider.add(Action.VOLUME_DOWN.setTarget(Target.APPLICATION));
    }
}
