﻿<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="ProductDetails.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.ProductDetails" %>

<%@ Register Assembly="System.Web.DataVisualization, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35"
    Namespace="System.Web.UI.DataVisualization.Charting" TagPrefix="asp" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
    <style type="text/css">
        .style1
        {
            width: 430px;
        }
        .style2
        {
            width: 363px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" Font-Size="X-Large" ForeColor="Black"
        Text="Label"></asp:Label>
    <div style="height: 22px">
    </div>
    <div>
        <asp:Label ID="Label9" runat="server" Text="商品图片"></asp:Label>
    </div>
    <div>
    </div>
    <div>
        <asp:Image ID="ProductImage" runat="server" />
        <div>
        </div>
    </div>
    <asp:Label ID="Label1" runat="server" Text="商品名称"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="ProductNameTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label2" runat="server" Text="商品描述"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="ProductDescriptionTextBox" runat="server" Height="140px" TextMode="MultiLine"
        Width="442px" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label3" runat="server" Text="商品分类"></asp:Label>
    <div>
    </div>
    <div>
        <asp:Label ID="CategoryLabel" runat="server" Text="Label"></asp:Label>
        <div>
        </div>
    </div>
    <asp:Label ID="Label4" runat="server" Text="商品子分类"></asp:Label>
    <div>
    </div>
    <div>
        <asp:Label ID="SecCategoryLabel" runat="server" Text="Label"></asp:Label>
        <div>
        </div>
    </div>
    <asp:Label ID="Label5" runat="server" Text="条形码"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="BarcodeTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label6" runat="server" Text="价格"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="PriceTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label7" runat="server" Text="品牌"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="BrandTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    <div>
    </div>
    <asp:Label ID="Label8" runat="server" Text="产地"></asp:Label>
    <div>
    </div>
    <div>
        <asp:TextBox ID="LocationTextBox" runat="server" BorderStyle="None" ReadOnly="True"></asp:TextBox>
    </div>
    <table style="width: 100%;">
        <tr>
            <td class="style1">
                &nbsp;
            </td>
            <td class="style2">
                <asp:Button ID="EditButton" runat="server" Height="39px" Text="编辑" Width="105px"
                    OnClick="EditButton_Click" />
            </td>
            <td>
                <asp:Button ID="DeleteButton" runat="server" Height="39px" Text="删除" Width="105px"
                    OnClick="DeleteButton_Click" />
            </td>
        </tr>
        <tr>
            <td class="style1">
                &nbsp;
            </td>
            <td class="style2">
                &nbsp;
            </td>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <td class="style1">
                &nbsp;
            </td>
            <td class="style2">
                &nbsp;
            </td>
            <td>
                &nbsp;
            </td>
        </tr>
    </table>
    <asp:ObjectDataSource ID="ProductsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Product"
        DeleteMethod="DeleteProduct" InsertMethod="InsertProduct" SelectMethod="GetProduct"
        TypeName="NFCShoppingWebSite.BLL.ProductBL" UpdateMethod="UpdateProduct">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="0" Name="id" QueryStringField="productID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="product" Type="Object" />
            <asp:Parameter Name="origProduct" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="ReviewsDataSourse" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Review"
        DeleteMethod="DeleteReview" SelectMethod="GetReviewsByProductID" TypeName="NFCShoppingWebSite.BLL.ReviewBL">
        <SelectParameters>
            <asp:QueryStringParameter Name="pid" QueryStringField="productID" Type="Int32" />
        </SelectParameters>
    </asp:ObjectDataSource>
    <asp:ScriptManager ID="ScriptManager" runat="server" EnablePartialRendering="true">
    </asp:ScriptManager>
    <asp:UpdatePanel ID="UpdatePane" runat="server">
        <ContentTemplate>
            <asp:ListView ID="ReviewListView" runat="server" AllowPaging="true" DataKeyNames="reviewID"
                DataSourceID="ReviewsDataSourse">
                <AlternatingItemTemplate>
                    <li style="background-color: #FAFAD2; color: #284775;">reviewID:
                        <asp:Label ID="reviewIDLabel" runat="server" Text='<%# Eval("reviewID") %>' />
                        <br />
                        userID:
                        <asp:Label ID="userIDLabel" runat="server" Text='<%# Eval("userID") %>' />
                        <br />
                        productID:
                        <asp:Label ID="productIDLabel" runat="server" Text='<%# Eval("productID") %>' />
                        <br />
                        comment:
                        <asp:Label ID="commentLabel" runat="server" Text='<%# Eval("comment") %>' />
                        <br />
                        rating:
                        <asp:Label ID="ratingLabel" runat="server" Text='<%# Eval("rating") %>' />
                        <br />
                        createAt:
                        <asp:Label ID="createAtLabel" runat="server" Text='<%# Eval("createAt") %>' />
                        <br />
                        Product:
                        <asp:Label ID="ProductLabel" runat="server" Text='<%# Eval("Product") %>' />
                        <br />
                        ProductReference:
                        <asp:Label ID="ProductReferenceLabel" runat="server" Text='<%# Eval("ProductReference") %>' />
                        <br />
                        User:
                        <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User") %>' />
                        <br />
                        UserReference:
                        <asp:Label ID="UserReferenceLabel" runat="server" Text='<%# Eval("UserReference") %>' />
                        <br />
                        EntityState:
                        <asp:Label ID="EntityStateLabel" runat="server" Text='<%# Eval("EntityState") %>' />
                        <br />
                        EntityKey:
                        <asp:Label ID="EntityKeyLabel" runat="server" Text='<%# Eval("EntityKey") %>' />
                        <br />
                        <asp:Button ID="DeleteButton" runat="server" CommandName="Delete" Text="删除" />
                    </li>
                </AlternatingItemTemplate>
                <EditItemTemplate>
                    <li style="background-color: #FFCC66; color: #000080;">reviewID:
                        <asp:TextBox ID="reviewIDTextBox" runat="server" Text='<%# Bind("reviewID") %>' />
                        <br />
                        userID:
                        <asp:TextBox ID="userIDTextBox" runat="server" Text='<%# Bind("userID") %>' />
                        <br />
                        productID:
                        <asp:TextBox ID="productIDTextBox" runat="server" Text='<%# Bind("productID") %>' />
                        <br />
                        comment:
                        <asp:TextBox ID="commentTextBox" runat="server" Text='<%# Bind("comment") %>' />
                        <br />
                        rating:
                        <asp:TextBox ID="ratingTextBox" runat="server" Text='<%# Bind("rating") %>' />
                        <br />
                        createAt:
                        <asp:TextBox ID="createAtTextBox" runat="server" Text='<%# Bind("createAt") %>' />
                        <br />
                        Product:
                        <asp:TextBox ID="ProductTextBox" runat="server" Text='<%# Bind("Product") %>' />
                        <br />
                        ProductReference:
                        <asp:TextBox ID="ProductReferenceTextBox" runat="server" Text='<%# Bind("ProductReference") %>' />
                        <br />
                        User:
                        <asp:TextBox ID="UserTextBox" runat="server" Text='<%# Bind("User") %>' />
                        <br />
                        UserReference:
                        <asp:TextBox ID="UserReferenceTextBox" runat="server" Text='<%# Bind("UserReference") %>' />
                        <br />
                        EntityState:
                        <asp:TextBox ID="EntityStateTextBox" runat="server" Text='<%# Bind("EntityState") %>' />
                        <br />
                        EntityKey:
                        <asp:TextBox ID="EntityKeyTextBox" runat="server" Text='<%# Bind("EntityKey") %>' />
                        <br />
                        <asp:Button ID="UpdateButton" runat="server" CommandName="Update" Text="更新" />
                        <asp:Button ID="CancelButton" runat="server" CommandName="Cancel" Text="取消" />
                    </li>
                </EditItemTemplate>
                <EmptyDataTemplate>
                    未返回数据。
                </EmptyDataTemplate>
                <InsertItemTemplate>
                    <li style="">reviewID:
                        <asp:TextBox ID="reviewIDTextBox" runat="server" Text='<%# Bind("reviewID") %>' />
                        <br />
                        userID:
                        <asp:TextBox ID="userIDTextBox" runat="server" Text='<%# Bind("userID") %>' />
                        <br />
                        productID:
                        <asp:TextBox ID="productIDTextBox" runat="server" Text='<%# Bind("productID") %>' />
                        <br />
                        comment:
                        <asp:TextBox ID="commentTextBox" runat="server" Text='<%# Bind("comment") %>' />
                        <br />
                        rating:
                        <asp:TextBox ID="ratingTextBox" runat="server" Text='<%# Bind("rating") %>' />
                        <br />
                        createAt:
                        <asp:TextBox ID="createAtTextBox" runat="server" Text='<%# Bind("createAt") %>' />
                        <br />
                        Product:
                        <asp:TextBox ID="ProductTextBox" runat="server" Text='<%# Bind("Product") %>' />
                        <br />
                        ProductReference:
                        <asp:TextBox ID="ProductReferenceTextBox" runat="server" Text='<%# Bind("ProductReference") %>' />
                        <br />
                        User:
                        <asp:TextBox ID="UserTextBox" runat="server" Text='<%# Bind("User") %>' />
                        <br />
                        UserReference:
                        <asp:TextBox ID="UserReferenceTextBox" runat="server" Text='<%# Bind("UserReference") %>' />
                        <br />
                        EntityState:
                        <asp:TextBox ID="EntityStateTextBox" runat="server" Text='<%# Bind("EntityState") %>' />
                        <br />
                        EntityKey:
                        <asp:TextBox ID="EntityKeyTextBox" runat="server" Text='<%# Bind("EntityKey") %>' />
                        <br />
                        <asp:Button ID="InsertButton" runat="server" CommandName="Insert" Text="插入" />
                        <asp:Button ID="CancelButton" runat="server" CommandName="Cancel" Text="清除" />
                    </li>
                </InsertItemTemplate>
                <ItemSeparatorTemplate>
                    <br />
                </ItemSeparatorTemplate>
                <ItemTemplate>
                    <li style="background-color: #FFFBD6; color: #333333;">reviewID:
                        <asp:Label ID="reviewIDLabel" runat="server" Text='<%# Eval("reviewID") %>' />
                        <br />
                        userID:
                        <asp:Label ID="userIDLabel" runat="server" Text='<%# Eval("userID") %>' />
                        <br />
                        productID:
                        <asp:Label ID="productIDLabel" runat="server" Text='<%# Eval("productID") %>' />
                        <br />
                        comment:
                        <asp:Label ID="commentLabel" runat="server" Text='<%# Eval("comment") %>' />
                        <br />
                        rating:
                        <asp:Label ID="ratingLabel" runat="server" Text='<%# Eval("rating") %>' />
                        <br />
                        createAt:
                        <asp:Label ID="createAtLabel" runat="server" Text='<%# Eval("createAt") %>' />
                        <br />
                        Product:
                        <asp:Label ID="ProductLabel" runat="server" Text='<%# Eval("Product") %>' />
                        <br />
                        ProductReference:
                        <asp:Label ID="ProductReferenceLabel" runat="server" Text='<%# Eval("ProductReference") %>' />
                        <br />
                        User:
                        <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User") %>' />
                        <br />
                        UserReference:
                        <asp:Label ID="UserReferenceLabel" runat="server" Text='<%# Eval("UserReference") %>' />
                        <br />
                        EntityState:
                        <asp:Label ID="EntityStateLabel" runat="server" Text='<%# Eval("EntityState") %>' />
                        <br />
                        EntityKey:
                        <asp:Label ID="EntityKeyLabel" runat="server" Text='<%# Eval("EntityKey") %>' />
                        <br />
                        <asp:Button ID="DeleteButton" runat="server" CommandName="Delete" Text="删除" />
                    </li>
                </ItemTemplate>
                <LayoutTemplate>
                    <ul id="itemPlaceholderContainer" runat="server" style="font-family: Verdana, Arial, Helvetica, sans-serif;">
                        <li runat="server" id="itemPlaceholder" />
                    </ul>
                    <div style="text-align: center; background-color: #FFCC66; font-family: Verdana, Arial, Helvetica, sans-serif;
                        color: #333333;">
                        <asp:DataPager ID="DataPager" runat="server" PageSize="4">
                            <Fields>
                                <asp:NextPreviousPagerField ButtonType="Button" ShowFirstPageButton="True" ShowLastPageButton="True" />
                            </Fields>
                        </asp:DataPager>
                    </div>
                </LayoutTemplate>
            </asp:ListView>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
