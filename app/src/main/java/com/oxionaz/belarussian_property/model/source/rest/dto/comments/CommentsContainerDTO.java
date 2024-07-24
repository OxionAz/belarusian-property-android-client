package com.oxionaz.belarussian_property.model.source.rest.dto.comments;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;

public class CommentsContainerDTO {

    @SerializedName("comments")
    @Expose
    private List<CommentsDTO> commentsDTOs;
    @SerializedName("info")
    @Expose
    private InfoTable infoTable;

    public List<CommentsDTO> getCommentsDTOs() {
        return commentsDTOs;
    }

    public void setCommentsDTOs(List<CommentsDTO> commentsDTOs) {
        this.commentsDTOs = commentsDTOs;
    }

    public InfoTable getInfo() {
        return infoTable;
    }

    public void setInfo(InfoTable info) {
        this.infoTable = info;
    }

}
