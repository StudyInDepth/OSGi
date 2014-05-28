package postgraduatems.persistence.entities;

/**
 * Created by dotoan on 4/15/14.
 */
public interface ThesisState {
    String NOT_STARTED = "Chưa bắt đầu";
    String BEING_PREPARED = "Chuẩn bị đề tài";
    String BEING_VERIFIED = "Đợi duyệt đề cương";
    String STARTED = "Đang thực hiện";
    String PRESENTATION_PREPARED = "Chuẩn bị bảo vệ";
}
