package entities;

public class CourseFactory {
    private enum Names {MATH, ENGLISH, HISTORY}
    public static Course getClassFromFactory(String id,Teacher teacher)
    {
        Names name = Names.valueOf(id.toUpperCase());
        switch(name)
        {
            case MATH:
                return new MathCourse(teacher);
            case ENGLISH:
                return new EnglishCourse(teacher);
            case HISTORY:
                return new HistoryCourse(teacher);
            default :
                throw new EnumConstantNotPresentException( Names.class, name.name());
        }
    }
}
