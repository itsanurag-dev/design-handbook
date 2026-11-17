package dev.anurag.handbook.lld.instagram;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Represents the core Instagram system design with users, media, invitations, and relations.
 */
public class Instagram {
    /** Invitation status for user connections. */
    public enum InvitationStatus { PENDING, ACCEPTED, REJECTED, CANCELED }
    /** Account status for user profiles. */
    public enum AccountStatus { PUBLIC, PRIVATE, CLOSED }
    /** Media status for posts. */
    public enum MediaStatus { PUBLIC, PRIVATE }
    /** Media type for posts. */
    public enum MediaType { PHOTO, VIDEO }

    /** Address details for a user. */
    public class AddressDetails {
        private String streetAddress;
        private String city;
        private String country;
    }

    /** Account details for a user. */
    public class AccountDetails {
        private Date createdTime;
        private AccountStatus status;
        private boolean updateAccountStatus(AccountStatus accountStatus) { return false; }
    }

    /** Invitation for user connection. */
    public class Invitation {
        private Integer userID;
        private InvitationStatus status;
        private Date sentDate;
        public boolean updateInvitation(InvitationStatus status) { return false; }
    }

    /** Pending invitation for user connection. */
    public class PendingInvitation extends Invitation {
        public boolean acceptConnection() { return false; }
        public boolean rejectConnection() { return false; }
    }

    /** User relations (followers, following, invitations). */
    public class UserRelations {
        private HashSet<Integer> userFollower;
        private HashSet<Integer> userFollowing;
        private HashSet<ConnectionInvitation> connectionInvitations;
    }

    /** Comment on a media post. */
    public class Comment {
        private Integer id;
        private User addedBy;
        private Date addedDate;
        private String comment;
        public boolean updateComment(String comment) { return false; }
    }

    /** Media post (photo or video). */
    public class Media {
        private Integer id;
        private User createdBy;
        private MediaType mediaType;
        private String path;
        private MediaStatus mediaStatus;
        private int viewsCount;
        private HashSet<Integer> userLikes;
        private HashSet<Integer> userComments;
    }

    /** User profile. */
    public class User {
        private int id;
        private String password;
        private String nickname;
        private String email;
        private AddressDetails addressDetails;
        private AccountDetails accountDetails;
        private UserRelations userRelations;
        private HashSet<ConnectionInvitation> invitationsByMe;
        private HashSet<ConnectionInvitation> invitationsToMe;
        public boolean updatePassword() { return false; }
        public boolean createMedia(Media media) { return false; }
        public boolean updateMedia(int mediaId, MediaStatus mediaStatus) { return false; }
        public boolean sendInvitation(ConnectionInvitation invitation) { return false; }
        public List<User> searchUser(String term) { return null; }
        public List<Media> searchMedia(String term) { return null; }
    }
}
