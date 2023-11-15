package promotion;

public enum ChristmasBadge {
    SANTA(20000, "산타"),
    TREE(10000, "트리"),
    STAR(5000, "별"),
    NONE(0, "없음");

    ChristmasBadge(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public static ChristmasBadge calculateBadge(int totalPromotionPrice){
        ChristmasBadge returnBadge = ChristmasBadge.NONE;
        for(ChristmasBadge badge : ChristmasBadge.values()){
            if(badge.cost <= totalPromotionPrice && badge.cost > returnBadge.cost){
                returnBadge = badge;
            }
        }
        return returnBadge;
    }

    private final int cost;
    private final String name;

    public String getName() { return name; }
}
