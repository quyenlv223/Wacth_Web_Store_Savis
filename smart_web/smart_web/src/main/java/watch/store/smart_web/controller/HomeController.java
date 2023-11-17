package watch.store.smart_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import watch.store.smart_web.dto.respone.color.ColorRespone;
import watch.store.smart_web.dto.respone.product.NewProductRespone;
import watch.store.smart_web.entity.CategoryEntity;
import watch.store.smart_web.entity.ColorEntity;
import watch.store.smart_web.service.CategoryService;
import watch.store.smart_web.service.ColorService;
import watch.store.smart_web.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ColorService colorService;



    @GetMapping("/all")
    public String trangChu1(Model model , @RequestParam("page") String page) {
        //PageRequest pageRequest = PageRequest.of(Integer.parseInt(page) - 1, 16);
        List<NewProductRespone> list = productService.findAll(PageRequest.of(Integer.valueOf(page) - 1, 8));
        model.addAttribute("list" , list);
        return "views/home/shop";
    }



    @GetMapping("")
    public String trangChu(Model model) {
        List<NewProductRespone> listAppleWatch = productService.findByAppleWatch();
        List<NewProductRespone> listSS = productService.findBySamSung();
        List<NewProductRespone> listXiaomi = productService.findByXiaomi();
        List<NewProductRespone> listHuawei = productService.findByHuawei();
        List<NewProductRespone> listTop10 = productService.findbyTop10();
        List<NewProductRespone> listTop3 = productService.findbyTop3();
        List<NewProductRespone> listGarmin = productService.findByGarmin();
        List<NewProductRespone> listRandom = productService.findbyRandom();


        List<NewProductRespone> list = new ArrayList<>();
        if (listRandom.size() > 2){
            for(int i = listRandom.size() - 1; i > listRandom.size() -3; i--){
                list.add(listRandom.get(i));
            }
        }

        model.addAttribute("list", list);
        model.addAttribute("listAppleWatch", listAppleWatch);
        model.addAttribute("listSS", listSS);
        model.addAttribute("listXiaomi", listXiaomi);
        model.addAttribute("listHuawei", listHuawei);
        model.addAttribute("listTop10", listTop10);
        model.addAttribute("listTop3", listTop3);
        model.addAttribute("listGarmin", listGarmin);
        model.addAttribute("listRandom", listRandom);


        //        category
        List<CategoryEntity> categoryEntities =  categoryService.findByCategoryAndDeleteFlagIsFalse();
        model.addAttribute("category", categoryEntities);
        return "views/home/home-1";
    }
    @GetMapping("{id}")
    public String getProductByCategori(Model model, @PathVariable("id") String id) {
        List<NewProductRespone> listIps = productService.findByCateId(Long.parseLong(id));
        model.addAttribute("list", listIps);
        return "views/home/shop";
    }
 @GetMapping("/category={name}")
    public String category(Model model
         , @PathVariable("name") Long cate
           ,@RequestParam(name = "color", required = false) Long color
           , @RequestParam(name = "price1", required = false) Long price1
         , @RequestParam(name = "price2", required = false) Long price2
 ){
    List<ColorRespone> listcolors = colorService.findAll();
     List<NewProductRespone> listcate = productService.findbycolor(cate, color, price1, price2);
//     System.out.println(colors);
     model.addAttribute( "listcate", listcate);
     model.addAttribute("colors", listcolors);
         return "views/home/home-cate";
 }

    @PostMapping("/color")
    public String filterByColor(@RequestParam(name = "selectedColors", required = false) List<Long> listcolor ,
                                @PathVariable("name") Long cate
                                ,HttpServletRequest request
                                ,@RequestBody FilterRequest filterRequest
                                , Model model
    ){
        Long color = !listcolor.isEmpty() ? listcolor.get(0) : null;


        List<Long> selectedColors = filterRequest.getSelectedColors();
        List<ColorRespone> listcolors = colorService.findAll();
        List<NewProductRespone> listcate = productService.findbycolor(cate, color, null, null);
        String currentUrl = request.getRequestURL().toString();

//        // Thêm tham số colors vào URL nếu danh sách màu tồn tại
//        if (selectedColors != null && !selectedColors.isEmpty()) {
//            currentUrl += "?colors=" + String.join("&colors=", selectedColors);
//        }



         return "redirect:" + currentUrl;
    }


    static class FilterRequest {
        private List<Long> selectedColors;

        public List<Long> getSelectedColors() {
            return selectedColors;
        }

        public void setSelectedColors(List<Long> selectedColors) {
            this.selectedColors = selectedColors;
        }
    }


}
