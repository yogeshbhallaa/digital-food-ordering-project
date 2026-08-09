
import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.event.BrowserEvent;
import com.teamdev.jxbrowser.browser.event.TitleChanged;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.navigation.event.NavigationStarted;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class helloworld {

    public helloworld() {
        // Creating and running Chromium engine
        System.out.println("hello world");
        EngineOptions options
                = EngineOptions
                        .newBuilder(HARDWARE_ACCELERATED)
                        .licenseKey("1BNDHFSC1FSIT6M7Z6TSZWQUGRO9U9Q9YSXVM5WA82ZPKQM2UPXOT595T4VR064DCWF7BF")
                        .build();
        Engine engine = Engine.newInstance(options);
        Browser browser = engine.newBrowser();

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance.
            BrowserView view = BrowserView.newInstance(browser);

            // Creating and displaying Swing app frame.
            JFrame frame = new JFrame("Payment Demo");
            // Close Engine and onClose app window
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("engine closed");
                    engine.close();
                }
            });
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            int amount = Global.billamount;

            browser.navigation().on(NavigationStarted.class, event -> {
                String url = event.url();
                System.out.println("url : " + url);
                if (url.contains("BookingSuccess.html")) {
                    String ss = url.substring(url.indexOf("?") + 1);
                    String id = ss.substring(ss.indexOf("=") + 1);
                    System.out.println("payment success");
                    generatebill obj = new generatebill();
                    frame.setVisible(false);
                    //frame.dispose();
                }
            });
            browser.navigation().loadUrl("http://amrinder.vmm.education/paymentdemo.html?amount=" + amount
                    + "&name=Food Cirlce&desc=Bill Amount&photo=https://images-na.ssl-images-amazon.com/images/I/61yMQIMMODL._SY606_.jpg");
            frame.add(view, BorderLayout.CENTER);
            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
