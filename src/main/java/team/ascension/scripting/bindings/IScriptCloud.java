package team.ascension.scripting.bindings;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.annotation.ScriptFunction;
import team.ascension.scripting.annotation.ScriptParameter;

@ScriptClass(name = "Cloud")
public interface IScriptCloud<T, E> {

    @ScriptFunction(name = "uploadFile", documentation = "Uploads a file to the users cloud.")
    void uploadFile(@ScriptParameter(name = "path") final String path, @ScriptParameter(name = "contents") final String contents);

    @ScriptFunction(name = "deleteFile", documentation = "Deletes a file within the users cloud.")
    void deleteFile(@ScriptParameter(name = "path") final String path);

    @ScriptFunction(name = "shareFile", documentation = "Generates a share key for the provided file within the users cloud.")
    void shareFile(@ScriptParameter(name = "path") final String path, @ScriptParameter(name = "maxUsers") final int maxUsers, @ScriptParameter(name = "duration") final long duration);

    @ScriptFunction(name = "getSharedFile", documentation = "Gets a file from a share key.")
    String getSharedFile(@ScriptParameter(name = "key") final String key);

    @ScriptFunction(name = "getFiles", documentation = "Gets all files within a directory in the users cloud.")
    String[] getFiles(@ScriptParameter(name = "path") final String path);

    @ScriptFunction(name = "doesFileExist", documentation = "Returns true if the provided file path exists within the users cloud. False if not.")
    boolean doesFileExist(@ScriptParameter(name = "path") final String path);

    @ScriptFunction(name = "getUsedStorage", documentation = "Returns the unused storage available within the users cloud in megabytes.")
    long getAvailableStorage();

    @ScriptFunction(name = "getUsedStorage", documentation = "Returns the maximum storage available within the users cloud in megabytes.")
    long getTotalAvailableStorage();

    @ScriptFunction(name = "getUsedStorage", documentation = "Returns the storage used within the users cloud in megabytes.")
    long getUsedStorage();

    @ScriptFunction(name = "getUser", returnType = "User", documentation = "Returns a user by their id.")
    T getUser(final int id);

    @ScriptFunction(name = "getLocalUser", returnType = "User", documentation = "Returns the local user.")
    T getLocalUser();

    @ScriptFunction(name = "getFriendRequests", returnType = "FriendRequest[]", documentation = "Returns the users friend requests.")
    E[] getFriendRequests();

}
