public class Task implements Comparable {
    public String name;
    public String start;
    public int duration;
    public int importance;
    public boolean urgent;

    /*
        Getter methods
     */
    public String getName() {
        return this.name;
    }

    public String getStartTime() {
        return this.start;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getImportance() {
        return this.importance;
    }

    public boolean isUrgent() {
        return this.urgent;
    }

    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    public String getFinishTime() {
        int startHour = Integer.parseInt(start.substring(0, 2));
        int startMinute = Integer.parseInt(start.substring(3));
        int durationHour = (int) duration; 
        int durationMinute = (int) ((duration - durationHour) * 60); 
        int endHour = (startHour + durationHour + (startMinute + durationMinute) / 60) % 24;
        int endMinute = (startMinute + durationMinute) % 60;
        String endTime = String.format("%02d:%02d", endHour, endMinute);
        
        return endTime;
    }

    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    public double getWeight() {
    	
        // YOUR CODE HERE
        return (double) importance * (urgent ? 2000 : 1) / duration;

    }

    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    @Override
    public int compareTo(Object o) {
    	String thisFinishTime = getFinishTime();
        String otherFinishTime = ((Task) o).getFinishTime();
        return thisFinishTime.compareTo(otherFinishTime);
    }
}
