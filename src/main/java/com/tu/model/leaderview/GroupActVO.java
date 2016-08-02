package com.tu.model.leaderview;

import java.util.*;

import com.tu.model.activities.ActivityVO;
import com.tu.model.user.act.UserActVO;

public class GroupActVO implements java.io.Serializable {

	private static final long serialVersionUID = 5165359653259615392L;
	
	private ActivityVO activity;
	private Float sum;
	private Integer nbParticipants;
	private List<UserActVO> members;
	

	// Constructors
	/** default constructor */
	public GroupActVO() {
		// do nothing
	}

	public Float getSum() {
		return this.sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
	}

	public List<UserActVO> getMemberInVO() {
		return members;
	}

	public void setMemberInVO(List<UserActVO> memberInVO) {
		this.members = memberInVO;
	}

	public Integer getNbParticipants() {
		return nbParticipants;
	}

	public void setNbParticipants(Integer nbParticipants) {
		this.nbParticipants = nbParticipants;
	}

	public ActivityVO getActivity() {
		return activity;
	}

	public void setActivity(ActivityVO activity) {
		this.activity = activity;
	}

}
