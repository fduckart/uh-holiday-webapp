package edu.hawaii.its.holiday.service;

import java.util.List;

import edu.hawaii.its.holiday.type.Action;
import edu.hawaii.its.holiday.type.ActionLog;

public interface ActionService {
    public List<Action> findActions();

    public void record(ActionLog actionLog);

    public long logCount();
}
