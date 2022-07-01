package ua.hillel.chatapp.security;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CredentialUtils {
    /**
     * @param credentialsLine - Request credentials sample: -login -u=<username> -p=<password>
     * @return array that contains username as first item and password as second one
     */
    public String[] parse(String credentialsLine) {
        String[] credentialsSegments = credentialsLine.split("\\s");
        String username = credentialsSegments[1].split("=")[1];
        String password = credentialsSegments[2].split("=")[1];
        String isSuperUser = credentialsSegments.length > 3 ? credentialsSegments[3] : "";
        return new String[]{username, password, isSuperUser};
    }
}
