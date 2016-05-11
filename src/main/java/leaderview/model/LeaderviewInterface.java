package leaderview.model;

import java.util.List;

public interface LeaderviewInterface {
	List<GroupActVO> doGetAllUserActsByGroupId(Integer groupId);

}
