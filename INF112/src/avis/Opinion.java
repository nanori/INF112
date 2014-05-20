package avis;

public class Opinion {
	
	private boolean opinion; 
	
	private Avis avis;
	
	public Opinion (boolean opinion, Avis avis){
		this.opinion = opinion;
		this.avis = avis;
	}
	
	public void updateOpinion(boolean opinion){
		this.opinion = opinion;
	}
	
	public boolean avisEquals (Avis avis){
		if(this.avis.equals(avis))
			return true;

		return false;
	}
	
	public int getOpinionMark (){
		if(this.opinion)
			return 1;
		
		return -1;
	}
}
