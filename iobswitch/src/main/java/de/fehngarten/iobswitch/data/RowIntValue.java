package de.fehngarten.iobswitch.data;

//import android.support.annotation.NonNull;

import androidx.annotation.NonNull;

public class RowIntValue implements Comparable<RowIntValue> {

    public String name;
    public String unit;
    public String value;
    public String setCommand;
    public Float stepSize;
    public int commandExecDelay;
    public boolean isTime;

    public void transfer(ConfigIntValueRow configIntValueRow) {
        name = configIntValueRow.name;
        unit = configIntValueRow.unit;
        value = configIntValueRow.value;
        setCommand = configIntValueRow.setCommand;
        stepSize = configIntValueRow.stepSize;
        if (configIntValueRow.isTime == null) {
            isTime = false;
        } else {
            isTime = configIntValueRow.isTime;
        }
        commandExecDelay = configIntValueRow.commandExecDelay;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    //public int compareTo(@NonNull RowIntValue compSwitch) {
    public int compareTo(@NonNull RowIntValue compSwitch) {
        return this.unit.compareToIgnoreCase(compSwitch.unit);
    }
}







