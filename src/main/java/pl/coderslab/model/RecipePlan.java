package pl.coderslab.model;


public class RecipePlan {
    private int id;
    private int recipeId;
    private String mealName;
    private int display_order;
    private int day_name_id;
    private int planId;
    private String recipeDescri;

    public RecipePlan() {
    }

    public RecipePlan(int id, int recipeId, String mealName, int display_order, int day_name_id, int planId, String recipeDescri) {
        this.id = id;
        this.recipeId = recipeId;
        this.mealName = mealName;
        this.display_order = display_order;
        this.day_name_id = day_name_id;
        this.planId = planId;
        this.recipeDescri = recipeDescri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public int getDay_name_id() {
        return day_name_id;
    }

    public void setDay_name_id(int day_name_id) {
        this.day_name_id = day_name_id;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getRecipeDescri() {
        return recipeDescri;
    }

    public void setRecipeDescri(String recipeDescri) {
        this.recipeDescri = recipeDescri;
    }

    @Override
    public String toString() {
        return "RecipePlan{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", mealName='" + mealName + '\'' +
                ", display_order=" + display_order +
                ", day_name_id=" + day_name_id +
                ", planId=" + planId +
                ", recipeDescri='" + recipeDescri + '\'' +
                '}';
    }
}
