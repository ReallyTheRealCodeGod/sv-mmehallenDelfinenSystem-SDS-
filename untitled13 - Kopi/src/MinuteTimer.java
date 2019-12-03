public class MinuteTimer implements Comparable {

    int minutes;
    int seconds;

    public MinuteTimer(int minutes, int seconds) {

        this.minutes = minutes;
        this.seconds = seconds;
    }

    public MinuteTimer(int seconds) {

        this.minutes = seconds/60;
        this.seconds = seconds%60;
    }

    @Override
    public int compareTo(Object o){

        MinuteTimer otherTime = (MinuteTimer) o;

        int tempSecOther = otherTime.minutes*60 + otherTime.seconds;
        int tempSecThis = this.minutes*60 + this.seconds;

        return tempSecThis - tempSecOther;

    }
}
