
package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GachaDAO;
import dto.PickupDTO;

@WebServlet("/GachaLoadingServlet")
public class GachaLoadingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("user_id");

        // 候補地リスト取得
        @SuppressWarnings("unchecked")
        List<PickupDTO> pickupList = (List<PickupDTO>) session.getAttribute("pickupList");

        // 候補地が存在しない場合
        if (pickupList == null || pickupList.isEmpty()) {
            request.setAttribute("errorMsg", "行きたい場所が登録されていません。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gachaError.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // 1日1回制限のチェック
        GachaDAO dao = new GachaDAO();
        if (dao.hasDrawnToday(userId)) {
            request.setAttribute("errorMsg", "本日のガチャはすでに引かれています。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gachaError.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // ランダムで1件選ぶ
        Random rand = new Random();
        PickupDTO selectedPickup = pickupList.get(rand.nextInt(pickupList.size()));

        // 結果を保存
        dao.saveResult(userId, selectedPickup.getPickup_id());

        // 結果をセッションへ
        session.setAttribute("selectedPickup", selectedPickup);

        // ローディング画面へ遷移（JSで結果に飛ぶ）
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gachaLoading.jsp");
        dispatcher.forward(request, response);
    }
}
