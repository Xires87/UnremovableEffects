package net.fryc.unremovableeffects.json;

public class ReplaceManager {

    private int currentReplacePriority;
    private boolean isReplacePresent;

    public ReplaceManager(){
        this.currentReplacePriority = 0;
        this.isReplacePresent = false;
    }

    public int getCurrentReplacePriority() {
        return currentReplacePriority;
    }

    public void setCurrentReplacePriority(int currentReplacePriority) {
        this.currentReplacePriority = currentReplacePriority;
    }

    public boolean isReplacePresent() {
        return isReplacePresent;
    }

    public void setReplacePresent(boolean replacePresent) {
        isReplacePresent = replacePresent;
    }
}
