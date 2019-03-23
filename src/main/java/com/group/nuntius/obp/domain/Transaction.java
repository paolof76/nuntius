package com.group.nuntius.obp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;
import java.util.List;

@Data
public class Transaction {
    public static final String ISO8601_TIMESTAMP_FORMAT = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'";

    private String id;

    @JsonProperty("other_account")
    private Account targetAccount;

    @JsonProperty("this_account")
    private Account ownAccount;

    private Details details;

    private Metadata metadata;

    @Data
    private class Details {
        private String type;
        private String description;

        @JsonProperty("posted")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO8601_TIMESTAMP_FORMAT, timezone = "UTC")
        private Date postedDate;

        @JsonProperty("completed")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO8601_TIMESTAMP_FORMAT, timezone = "UTC")
        private Date completedDate;

        @JsonProperty("new_balance")
        @JsonDeserialize(using = MoneyJson.MoneyDeserializer.class)
        private Money newBalance;

        @JsonProperty("value")
        @JsonDeserialize(using = MoneyJson.MoneyDeserializer.class)
        private Money value;
    }

    @Data
    public class Metadata {
        private String narrative;
        private List<Object> comments;
        private List<Tag> tags;
        private List<Image> images;

        @JsonProperty("where")
        private Location location;

        public String getNarrative() {
            return narrative;
        }

        public void setNarrative(String narrative) {
            this.narrative = narrative;
        }

        public List<Object> getComments() {
            return comments;
        }

        public void setComments(List<Object> comments) {
            this.comments = comments;
        }

        public List<Tag> getTags() {
            return tags;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }

    @Data
    @NoArgsConstructor
    public static class Tag {
        public Tag(String value) {
            this.value = value;
        }
        private String value;

        private String id;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO8601_TIMESTAMP_FORMAT, timezone = "UTC")
        @JsonProperty("date")
        private Date createdAt;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tag tag = (Tag) o;
            return id.equals(tag.id);
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + id.hashCode();
            return result;
        }
    }

    @Data
    public static class Image {
        @JsonProperty("image_URL")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
