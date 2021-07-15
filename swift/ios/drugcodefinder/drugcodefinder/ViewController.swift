//
//  ViewController.swift
//  drugcodefinder
//
//  Created by passioner on 2021/6/18.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var qrImageView: UIImageView!
    
    @IBOutlet weak var nameText: UITextField!
    
    @IBOutlet weak var displayCode: UILabel!
    
    
    var currentBrightness = 0.0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
//  下一行程式碼為app開啟時，游標直接在輸入欄位，虛擬鍵盤直接開啟。
        nameText.becomeFirstResponder()
        
    }
    
//  下一段函數為碰到畫面任何地方，收起虛擬鍵盤
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        nameText.resignFirstResponder()
    }
    
//  按下『Generate』執行的事情
    @IBAction func generateAction(_ sender: Any) {
        let myName = nameText.text
                if let name = myName{
                    let combinedString = "\(name)0019999999"
                    qrImageView.image = generateBarCode(Name:combinedString)
    }
    }
    
    
//  Barcode產生的程式碼
    func generateBarCode(Name:String) -> UIImage? {
        let name_data = Name.data(using: String.Encoding.ascii)
        if let filter = CIFilter(name: "CICode128BarcodeGenerator"){
            filter.setValue(name_data, forKey: "inputMessage")
            
            if let output = filter.outputImage{
                
                let transform = CGAffineTransform(scaleX: 3, y: 3)
                let fini = output.transformed(by: transform)
                
                // 亮度提升到最高
                UIScreen.main.brightness = 1.0
                displayCode.text = nameText.text
                nameText.text = ""
                return UIImage(ciImage: fini)
            }
        }
        return nil
        
    }
    
    }

