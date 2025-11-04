package characters.prerequisites;

import characters.Character;

import java.util.Locale;

// Requires the character to have a specific job or subclass by id or name.
public class JobExclusivePrerequisite implements Prerequisite {
    private final String requiredJobIdOrName;

    public JobExclusivePrerequisite(String idOrName) {
        this.requiredJobIdOrName = (idOrName == null) ? "" : idOrName.trim();
    }

    @Override
    public boolean isMetBy(Character character) {
        if (requiredJobIdOrName.isEmpty() || character == null) return false;
        String want = requiredJobIdOrName.trim().toLowerCase(Locale.ROOT);

        try {
            // Preferred: getJob() returns the job name string
            Object jobRes = character.getClass().getMethod("getJob").invoke(character);
            if (jobRes instanceof String) {
                return want.equals(((String) jobRes).trim().toLowerCase(Locale.ROOT));
            }
            // If getJob() returned an object, try common inspectors (getName/getId/toString)
            if (jobRes != null) {
                try {
                    Object nameV = jobRes.getClass().getMethod("getName").invoke(jobRes);
                    if (nameV != null && want.equals(nameV.toString().trim().toLowerCase(Locale.ROOT))) return true;
                } catch (Throwable ignored) {}
                try {
                    Object idV = jobRes.getClass().getMethod("getId").invoke(jobRes);
                    if (idV != null && want.equals(idV.toString().trim().toLowerCase(Locale.ROOT))) return true;
                } catch (Throwable ignored) {}
                try {
                    String s = jobRes.toString();
                    if (s != null && want.equals(s.trim().toLowerCase(Locale.ROOT))) return true;
                } catch (Throwable ignored) {}
            }
        } catch (Throwable ignored) {}
        return false;
    }

    @Override
    public String getDescription() {
        return "Requires job/subclass '" + requiredJobIdOrName + "'";
    }
}