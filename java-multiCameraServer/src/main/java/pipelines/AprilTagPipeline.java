package pipelines;

import org.opencv.core.Mat; 

import edu.wpi.first.apriltag.AprilTagDetection;
import edu.wpi.first.apriltag.AprilTagDetector;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AprilTagPipeline implements VisionPipeline {
    /**
     * All tags currently detected by the emitting camera
     */
    public AprilTagDetection[] detectedTags;

    public AprilTagPipeline(String family) {
        AprilTagDetection[] emptyTags = {};
        this.detectedTags = emptyTags;
    }

    @Override
    public void process(Mat cameraFrame) {
        // TODO! How can we take our existing logic and update it to run here?
        // This is the center of the vision pipeline
    }

    private static final int c_resolutionWidth = 640;
    public static double widthOffset(AprilTagDetection aprilTag) {
        double coordinateCenter = c_resolutionWidth/2; 
        double aprilTagcenter = aprilTag.getCenterX();
        double offset = aprilTagcenter - coordinateCenter;
        return offset; // / coordinateSpace.width
    }

    // 2. turn offset into movement direction
    public static double rotationRate(double offset)
    {
        double zeroAccuracy = 0.04;
        //double multipliedOffset = 200*offset; 
        SmartDashboard.putNumber("Offset", offset);
        if (offset < zeroAccuracy && offset > -zeroAccuracy) {
            SmartDashboard.putNumber("Centered", 1);
            return 0.0;
            
        } else if (offset < 0) {
            SmartDashboard.putNumber("Going right", 1);
            return 0.1;
        } else {
            SmartDashboard.putNumber("Going left", 1);
            return -0.1;
        }
    }   
    

}