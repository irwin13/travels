<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Trafun</title>

    <!-- Bootstrap -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/trafun.css" rel="stylesheet">

</head>
<body>
<div class="container full">


    <div class="navbar-default navbar-fixed-top navbar">
        <!-- search flight -->
        <div class="main-menu">
            <ul class="nav nav-pills" >
                <li ><a href="#">Flight</a></li>
                <li ><a href="#">Cara Pesan</a></li>
                <li ><a href="#">Contact</a></li>
                <li ><a href="#">Sub Agen</a></li>
            </ul>
        </div>
        <div class="second-nav">
            <div class="col-md-3 logo">
                <img src="./png/trafun.png" height="40px">
            </div>
            <div class="search-frame col-md-9">
                <div >
                    <div class="col-md-3">
                        <input class="input-text icon-orig" type="text"  placeholder="kota asal">
                        <input class="input-text icon-dest" type="text"  placeholder="kota tujuan">
                    </div>
                    <div class="col-md-3">
                        <input class="input-text icon-cal" type="text"  placeholder="tanggal berangkat">
                        <input class="input-text icon-cal" type="text"  placeholder="tanggal pulang">
                    </div>
                    <div class="col-md-3">
                        <input class="input-text icon-adult" type="text" placeholder="1">
                        <input class="input-text icon-teen" type="text" placeholder="0">
                        <input class="input-text icon-baby" type="text" placeholder="0">
                        <a href="flight-list.php"><button class="search-button" >cari tiket pesawat</button></a>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="section-slide">


        <img src="./png/slidetravel1.png" width="80%">

    </div>

    <div class="section-teaser">
        <div class="teaser">

            <div class="teaser-price"><h1>200</h1><br><span>jakarta-medan</span></div>
            <div class="teaser-price"><h1>250</h1><br><span>jogja-jakarta</span></div>
            <div class="teaser-price"><h1>300</h1><br><span>makassar-jakarta</span></div>
            <div class="teaser-price"><h1>400</h1><br><span>jakarta-bali</span></div>
            <div class="teaser-price"><h1>450</h1><br><span>jakarta-surabaya</span></div>

        </div>
    </div>
    <div class="section-info">

    </div>
    <div class="section-footer">

    </div>

</div>




</div>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="./js/bootstrap.min.js"></script>
</body>
</html>