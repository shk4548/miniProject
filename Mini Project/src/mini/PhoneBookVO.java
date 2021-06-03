package mini;

//	DTO 객체: 자바의 계층간 데이터 전송을 담당하는 객체
//	필드, getter/setter, toString, equals 정도의 메서드만 작성
//	반드시 기본 생성자가 있어야 함
//	일반적으로 로직 메서드는 작성하지 않는다
public class PhoneBookVO {
	private Long id;	//	PK
	private String name;
	private String hp;
	private String tel;

	//	생성자
	public PhoneBookVO() {

	}

	//	필수 필드 생성자
	public PhoneBookVO(Long id, String name, String hp, String tel) {
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.tel = tel;
	}

	public Long getid() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String gethp() {
		return hp;
	}
	
	public void set(String hp) {
		this.hp = hp;
	}
	
	public String gettel() {
		return tel;
	}
	
	public void settel(String tel) {
		this.tel = tel;
	}




	@Override
	public String toString() {
		return "PhoneBookVO [id=" + id + ",name=" + name + ", hp =" + hp + ", tel =" + tel + "]";
	}
}