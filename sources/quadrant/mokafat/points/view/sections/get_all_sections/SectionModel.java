package quadrant.mokafat.points.view.sections.get_all_sections;

/* JADX INFO: loaded from: classes.dex */
public class SectionModel {
    private String title;
    private int titleImage;

    public SectionModel(String title, int titleImage) {
        this.title = title;
        this.titleImage = titleImage;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleImage() {
        return this.titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }
}
