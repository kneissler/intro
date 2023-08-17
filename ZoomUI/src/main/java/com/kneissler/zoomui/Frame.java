package com.kneissler.zoomui;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame() {
       setTitle("Alias Zeichnung");
       add(new PaintComponent());
       setVisible(true);
       setSize(1000,1000);
    }

 }
