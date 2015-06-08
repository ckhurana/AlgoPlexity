package in.kicka55studios.algoplexity;

public class Algorithm {
    private int _id;
    private String _algoname;
    private String _desc;
    private String _avgcase;
    private String _worstcase;

    public Algorithm() {
    }

    public Algorithm(String algoname, String desc, String avgcase, String worstcase) {
        this._algoname = algoname;
        this._desc = desc;
        this._avgcase = avgcase;
        this._worstcase = worstcase;
    }

    public int get_id() {
        return _id;
    }

    public String get_algoname() {
        return _algoname;
    }

    public String get_desc() {
        return _desc;
    }

    public String get_avgcase() {
        return _avgcase;
    }

    public String get_worstcase() {
        return _worstcase;
    }
}
