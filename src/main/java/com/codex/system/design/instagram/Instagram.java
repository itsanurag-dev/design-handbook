
package com.codex.system.design.instagram;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Instagram {
	public enum InvitationStatus {
		PENDING, ACCEPTED, REJECTED, CANCELED
	}

	public enum AccountStatus {
		PUBLIC, PRIVATE, CLOSED
	}

	public enum MediaStatus {
		PUBLIC, PRIVATE
	}

	public enum MediaType {
		PHOTO, VIDEO
	}

	public class AddressDetails {
		private String streetAddress;
		private String city;
		private String country;

	}

	public class AccountDetails {
		private Date createdTime;
		private AccountStatus status;

		private boolean updateAccountStatus(AccountStatus accountStatus) {
			return false;
		}

	}

	public class Invitation {
		private Integer userID;
		private InvitationStatus status;
		private Date sentDate;

		public boolean updateInvitation(InvitationStatus status) {
			return false;
		}
	}

	public class PendingInvitation extends Invitation {
		public boolean acceptConnection() {
			return false;
		}

		public boolean rejectConnection() {
			return false;
		}

	}

	public class UserRelations {
		private HashSet<Integer> userFollower;
		private HashSet<Integer> userFollowing;
		private HashSet<ConnectionInvitation> connectionInvitations;

	}

	public class Comment {
		private Integer id;
		private User addedBy;
		private Date addedDate;
		private String comment;

		public boolean updateComment(String comment) {
			return false;
		}

	}

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

		public boolean updatePassword() {
			return false;
		}

		public boolean createMedia(Media media) {
			return false;
		}

		public boolean updateMedia(int mediaId, MediaStatus mediaStatus) {
			return false;
		}

		public boolean sendInvitation(ConnectionInvitation invitation) {
			return false;
		}

		public List<User> searchUser(String term) {
			return null;
		}

		public List<Media> searchMedia(String term) {
			return null;
		}

	}
}
