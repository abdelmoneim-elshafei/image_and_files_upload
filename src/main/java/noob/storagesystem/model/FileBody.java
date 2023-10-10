package noob.storagesystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileBody {
    private byte[] data;
    private String dataType;
}
