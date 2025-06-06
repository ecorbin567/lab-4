package usecase;

import api.GradeDataBase;
import api.MongoGradeDataBase;
import entity.Grade;
import entity.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;
        final Team team = gradeDataBase.getMyTeam();
        // Call the API to get all the grades for the course for all your team members
        final String[] members = team.getMembers();
        for (final String member : members) {
            Grade grade = gradeDataBase.getGrade(member, course);
            sum += grade.getGrade();
            count++;
        }

        if (count == 0) {
            return 0;
        }

        return sum / count;
    }
}
