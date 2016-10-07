package edu.hawaii.its.holiday.action;

import org.springframework.context.ApplicationListener;

public interface ActionListener extends ApplicationListener<ActionEvent> {
    public int mapSize();
}
