package pl.coderslab.model;

public class LastAddedPlanDetails {
  private String dayName;
  private String mealName;
  private String recipeName;
  private String recipeDescription;
  private String planName;

  public LastAddedPlanDetails() {}

  public LastAddedPlanDetails(
      String dayName,
      String mealName,
      String recipeName,
      String recipeDescription,
      String planName) {
    this.dayName = dayName;
    this.mealName = mealName;
    this.recipeName = recipeName;
    this.recipeDescription = recipeDescription;
    this.planName = planName;
  }

  @Override
  public String toString() {
    return "LastAddedPlanDetails{"
        + "dayName='"
        + dayName
        + '\''
        + ", mealName='"
        + mealName
        + '\''
        + ", recipeName='"
        + recipeName
        + '\''
        + ", recipeDescription='"
        + recipeDescription
        + '\''
        + ", planName='"
        + planName
        + '\''
        + '}';
  }

  public String getDayName() {
    return dayName;
  }

  public void setDayName(String dayName) {
    this.dayName = dayName;
  }

  public String getMealName() {
    return mealName;
  }

  public void setMealName(String mealName) {
    this.mealName = mealName;
  }

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }

  public String getRecipeDescription() {
    return recipeDescription;
  }

  public void setRecipeDescription(String recipeDescription) {
    this.recipeDescription = recipeDescription;
  }

  public String getPlanName() {
    return planName;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }
}
