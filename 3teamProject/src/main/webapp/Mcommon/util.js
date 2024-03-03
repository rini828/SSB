function getMember_id(){
	if(session.getAttribute("userId") != null){
		var member_id = session.getAttribute("userId");
		return member_id;
	}else{
		return null;
	}
}