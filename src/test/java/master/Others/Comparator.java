package master.Others;

/**
 * Describe:
 * Created by pengp on 2018/3/14.
 */
public class Comparator implements java.util.Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        if(o1.freq>o2.freq){return 1;}
        else if(o1.freq<o2.freq){return -1;}
        else{return 0;}
    }

}