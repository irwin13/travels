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
        <link href="./css/jquery-ui-1.10.2.custom-southstreet.min.css" rel="stylesheet">
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
                        <div>
                            <form action="#" name="searchForm" id="searchForm" onkeypress="return event.keyCode != 13;">
                                <div class="col-md-3">
                                    <select id="fromCity" name="fromCity" class="input-text icon-orig">
                                        <option value="">Kota Asal</option>
                                        <#list cityList as city>
                                        <option value="${city.id}">${city.cityName}</option>
                                        </#list>
                                    </select>
                                    <select id="destinationCity" name="destinationCity"class="input-text icon-dest">
                                        <option value="">Kota Tujuan</option>
                                        <#list cityList as city>
                                        <option value="${city.id}">${city.cityName}</option>
                                        </#list>
                                    </select>
                                    <!--
                                    <input class="input-text icon-orig" type="text" name="fromCity" id="fromCity"
                                           placeholder="kota asal" />
                                    <input class="input-text icon-dest" type="text" name="destinationCity" id="destinationCity"
                                           placeholder="kota tujuan" />
                                    -->
                                </div>
                                <div class="col-md-3">
                                    <input class="input-text icon-cal" type="text" name="landingDate"
                                           id="landingDate" placeholder="tanggal berangkat" />
                                    <!--
                                    <input class="input-text icon-cal" type="text"
                                           name="arrivalDate" id="arrivalDate" placeholder="tanggal pulang" />
                                    -->
                                </div>
                                <div class="col-md-3">
                                    <input class="input-text icon-adult" type="text" placeholder="1">
                                    <input class="input-text icon-teen" type="text" placeholder="0">
                                    <input class="input-text icon-baby" type="text" placeholder="0">
                                    <button type="button" class="search-button" onclick="doSearch()">cari tiket pesawat</button>
                                </div>
                            </form>
                        </div>
                        <div id="ticketContent"></div>
                    </div>
                </div>
            </div>
            <div class="section-slide">
                <h2>TRAFUN</h2>
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
        </div>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script type="text/javascript" src="./js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui-1.10.4.min.js"></script>
        <script type="text/javascript" src="./js/bootstrap.min.js"></script>
        <script>
            function doSearch() {
                jQuery.ajax({
                    type: "GET",
                    url: './airlineTicket/filter',
                    dataType: "text",
                    data : $('#searchForm').serialize(),
                    success: function(data, textStatus) {
                        var htmlContent;
                        htmlContent = "<table class='table' width='90%'>";
                        htmlContent += "<thead>";
                        htmlContent += "<tr>";
                        htmlContent += "<th>#</th>";
                        htmlContent += "<th>Kota Asal</th>";
                        htmlContent += "<th>Kota Tujuan</th>";
                        htmlContent += "<th>Tanggal Berangkat</th>";
                        htmlContent += "<th>Harga</th>";
                        htmlContent += "</tr>";
                        htmlContent += "</thead>";
                        htmlContent += "<tbody>";

                        $.each($.parseJSON(data), function(idx, obj) {
                            htmlContent += "<tr>";
                            htmlContent += "<td>" + (idx + 1) + "</td>";
                            htmlContent += "<td>" + obj.fromCity.cityName + "</td>";
                            htmlContent += "<td>" + obj.destinationCity.cityName + "</td>";
                            var date = new Date(obj.landingDate);
                            var year = date.getFullYear();
                            var month = date.getMonth() + 1;
                            var day = date.getDate();

                            htmlContent += "<td>" + day + "-" + month + "-" + year + "</td>";
                            htmlContent += "<td>" + obj.price + "</td>";
                            htmlContent += "</tr>";
                        });

                        htmlContent += "</tbody>";
                        htmlContent += "</table>";

                        $('#ticketContent').html(htmlContent);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        var html = textStatus + " " + errorThrown;
                        $('#ticketContent').html(html);
                    }

                });

            }

            jQuery.ajax({
                type: "GET",
                url: './airlineTicket/getAll',
                dataType: "text",
                data : $('#searchForm').serialize(),
                success: function(data, textStatus) {
                    var htmlContent;
                    htmlContent = "<table class='table' width='90%'>";
                    htmlContent += "<thead>";
                    htmlContent += "<tr>";
                    htmlContent += "<th>#</th>";
                    htmlContent += "<th>Kota Asal</th>";
                    htmlContent += "<th>Kota Tujuan</th>";
                    htmlContent += "<th>Tanggal Berangkat</th>";
                    htmlContent += "<th>Harga</th>";
                    htmlContent += "</tr>";
                    htmlContent += "</thead>";
                    htmlContent += "<tbody>";

                    $.each($.parseJSON(data), function(idx, obj) {
                        htmlContent += "<tr>";
                        htmlContent += "<td>" + (idx + 1) + "</td>";
                        htmlContent += "<td>" + obj.fromCity.cityName + "</td>";
                        htmlContent += "<td>" + obj.destinationCity.cityName + "</td>";

                        var date = new Date(obj.landingDate);
                        var year = date.getFullYear();
                        var month = date.getMonth() + 1;
                        var day = date.getDate();

                        htmlContent += "<td>" + day + "-" + month + "-" + year + "</td>";
                        htmlContent += "<td>" + obj.price + "</td>";
                        htmlContent += "</tr>";
                    });

                    htmlContent += "</tbody>";
                    htmlContent += "</table>";

                    $('#ticketContent').html(htmlContent);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    var html = textStatus + " " + errorThrown;
                    $('#ticketContent').html(html);
                }

            });

            var d = new Date();
            var thisYear = d.getFullYear();

            $( "#landingDate" ).datepicker({
                dateFormat: 'dd-mm-yy',
                changeMonth: true,
                changeYear: true,
                yearRange: "1900:" + thisYear,
                showOptions: { direction: "down" }
            });

        </script>
    </body>
</html>