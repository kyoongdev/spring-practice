package kyoongdev.kyoongdevspring.common;

public class ResponseWithIdDTO {

  private Long id;

  public ResponseWithIdDTO(Long id) {
    this.id = id;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
